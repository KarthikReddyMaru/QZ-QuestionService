package com.qz.questionservice.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.qz.questionservice.constants.Category;
import com.qz.questionservice.constants.Difficulty;
import lombok.Data;

import java.util.List;

@Data
public class QuestionDto implements Dto {
    @JsonProperty(access = JsonProperty.Access.READ_WRITE)
    private int questionId;
    private String question;
    private List<String> options;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String correctAnswer;
    private Category category;
    private Difficulty difficulty;
}
