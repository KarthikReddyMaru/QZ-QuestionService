package com.qz.questionservice.dto.mapper;

import com.qz.questionservice.dto.QuestionDto;
import com.qz.questionservice.model.Question;
import org.springframework.stereotype.Component;

@Component
public class QuestionToQuestionDto implements Mapper<Question, QuestionDto> {
    @Override
    public QuestionDto map(Question question) {
        QuestionDto questionDto = new QuestionDto();
        questionDto.setQuestionId(question.getId());
        questionDto.setQuestion(question.getQuestion());
        questionDto.setOptions(question.getOptions());
        questionDto.setCategory(question.getCategory());
        questionDto.setDifficulty(question.getDifficulty());
        return questionDto;
    }
}
