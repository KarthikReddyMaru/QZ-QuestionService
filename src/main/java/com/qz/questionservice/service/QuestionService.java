package com.qz.questionservice.service;

import com.qz.questionservice.constants.Category;
import com.qz.questionservice.constants.Difficulty;
import com.qz.questionservice.dto.QuestionDto;
import com.qz.questionservice.dto.QuestionResponseDto;
import com.qz.questionservice.dto.mapper.QuestionMapper;
import com.qz.questionservice.exception.QuestionNotFound;
import com.qz.questionservice.model.Question;
import com.qz.questionservice.repo.QuestionRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class QuestionService {

    private final QuestionRepo questionRepo;
    private final QuestionMapper mapper;

    public List<QuestionDto> findQuestionsByCategory(String category, int numOfQuestions) {
        List<Question> questions = questionRepo.findQuestionsByCategory(category, numOfQuestions);
        return questions.stream().map(mapper::mapToDto).toList();
    }

    public List<QuestionDto> findQuestionsByCategoryAndDifficulty(String category, String difficulty, int numOfQuestions) {
        List<Question> questions = questionRepo.findQuestionsByCategoryAndDifficulty(category, difficulty, numOfQuestions);
        return questions.stream().map(mapper::mapToDto).toList();
    }

    public QuestionDto findQuestionById(int id) throws QuestionNotFound {
        Optional<Question> question = questionRepo.findById(id);
        return mapper.mapToDto(question.orElseThrow(() -> new QuestionNotFound("No Question with ID " + id + " found"))); // Will throw later
    }

    public List<QuestionDto> findQuestionsByIds(List<Integer> questionIds) {
        List<Question> questions = questionRepo.findAllById(questionIds);
        return questions
                .stream()
                .map(mapper::mapToDto)
                .toList();
    }
    
    public List<Integer> findQuestionIdsByCategory(String category, int numberOfQuestions) {
        List<QuestionDto> questions = findQuestionsByCategory(category, numberOfQuestions);
        return questions
                .stream()
                .map(QuestionDto::getQuestionId)
                .toList();
    }

    public List<Integer> findQuestionIdsByCategoryAndDifficulty(
            Category category, int numberOfQuestions, Difficulty difficulty) {
        List<QuestionDto> questions = findQuestionsByCategoryAndDifficulty(category.name(), difficulty.name(), numberOfQuestions);
        return questions
                .stream()
                .map(QuestionDto::getQuestionId)
                .toList();
    }

    @Transactional
    public int saveQuestion(QuestionDto questionDto) {
        Question question = mapper.mapToEntity(questionDto);
        return Optional.of(questionRepo.save(question).getId()).orElseThrow(() -> new RuntimeException("Save failed")); // Will enhance later
    }

    @Transactional
    public Integer updateQuestion(QuestionDto questionDto) {
        Question question = mapper.mapToEntity(questionDto);
        return Optional.of(questionRepo.save(question).getId()).orElseThrow(() -> new RuntimeException("Update failed"));
    }

    @Transactional(readOnly = true)
    public Integer calculateScore(List<QuestionResponseDto> responses) {
        List<Integer> questionIds = responses.stream().map(QuestionResponseDto::getId).toList();
        Map<Integer, Question> questions = questionRepo.findAllById(questionIds).stream()
                .collect(Collectors.toUnmodifiableMap(Question::getId, Function.identity()));
        int totalScore = 0;
        for(QuestionResponseDto response : responses) {
            int questionId = response.getId();
            Question actualQuestion = questions.get(questionId);
            if(actualQuestion.getCorrectAnswer().equals(response.getSelectedAnswer()))
                totalScore += actualQuestion.getDifficulty().getScore();
        }
        return totalScore;
    }

}
