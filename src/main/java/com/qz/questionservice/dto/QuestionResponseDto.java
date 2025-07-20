package com.qz.questionservice.dto;

import lombok.Data;

@Data
public class QuestionResponseDto implements Dto {
    private int id;
    private String selectedAnswer;
}
