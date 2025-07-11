package com.qz.questionservice.dto.mapper;

import com.qz.questionservice.dto.QuestionDto;
import com.qz.questionservice.model.Question;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
public class QuestionMapper implements BiMapper<Question, QuestionDto> {
    @Override
    public QuestionDto mapToDto(Question question) {
        QuestionDto questionDto = new QuestionDto();
        questionDto.setQuestionId(question.getId());
        questionDto.setQuestion(question.getQuestion());
        questionDto.setOptions(question.getOptions());
        questionDto.setCategory(question.getCategory());
        questionDto.setDifficulty(question.getDifficulty());
        return questionDto;
    }

    @Override
    public Question mapToEntity(QuestionDto dto) {
        Question question = new Question();
        if(dto.getQuestionId() != 0) question.setId(dto.getQuestionId());
        question.setQuestion(dto.getQuestion());
        question.setOptions(dto.getOptions());
        question.setCorrectAnswer(dto.getCorrectAnswer());
        question.setCategory(dto.getCategory());
        question.setDifficulty(dto.getDifficulty());
        return question;
    }
}
