package com.qz.questionservice.dto;

import com.qz.questionservice.constants.Category;
import com.qz.questionservice.constants.Difficulty;
import lombok.Data;

import java.util.List;

@Data
public class QuestionDto {
    private int questionId;
    private String question;
    private List<String> options;
    private Category category;
    private Difficulty difficulty;
}
