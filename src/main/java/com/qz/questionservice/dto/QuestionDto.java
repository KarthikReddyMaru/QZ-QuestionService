package com.qz.questionservice.dto;

import com.qz.questionservice.constants.Difficulty;
import lombok.Data;

import java.util.List;

@Data
public class QuestionDto {
    private String question;
    private List<String> options;
    private String category;
    private Difficulty difficulty;
}
