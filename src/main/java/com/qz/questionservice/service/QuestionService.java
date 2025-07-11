package com.qz.questionservice.service;

import com.qz.questionservice.dto.QuestionDto;
import com.qz.questionservice.exception.QuestionNotFound;
import com.qz.questionservice.dto.mapper.QuestionToQuestionDto;
import com.qz.questionservice.model.Question;
import com.qz.questionservice.repo.QuestionRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class QuestionService {

    private final QuestionRepo questionRepo;
    private final QuestionToQuestionDto mapper;

    public List<QuestionDto> findQuestionsByCategory(String category, int numOfQuestions) {
        List<Question> questions = questionRepo.findQuestionsByCategory(category, numOfQuestions);
        return questions.stream().map(mapper::map).toList();
    }

    public List<QuestionDto> findQuestionsByCategoryAndDifficulty(String category, String difficulty, int numOfQuestions) {
        List<Question> questions = questionRepo.findQuestionsByCategoryAndDifficulty(category, difficulty, numOfQuestions);
        return questions.stream().map(mapper::map).toList();
    }

    public QuestionDto findQuestionById(int id) throws QuestionNotFound {
        Optional<Question> question = questionRepo.findById(id);
        return mapper.map(question.orElseThrow(() -> new QuestionNotFound("No Question with ID " + id + " found"))); // Will throw later
    }

    public List<QuestionDto> findQuestionsByIds(List<Integer> questionIds) {
        List<Question> questions = questionRepo.findAllById(questionIds);
        return questions
                .stream()
                .map(mapper::map)
                .toList();
    }
    
    public List<Integer> findQuestionIdsByCategory(String category, int numberOfQuestions) {
        List<QuestionDto> questions = findQuestionsByCategory(category, numberOfQuestions);
        return questions
                .stream()
                .map(QuestionDto::getQuestionId)
                .toList();
    }
}
