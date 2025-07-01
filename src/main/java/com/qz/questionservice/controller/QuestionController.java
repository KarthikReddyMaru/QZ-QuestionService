package com.qz.questionservice.controller;

import com.qz.questionservice.constants.Difficulty;
import com.qz.questionservice.dto.QuestionDto;
import com.qz.questionservice.exception.QuestionNotFound;
import com.qz.questionservice.service.QuestionService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/question")
@AllArgsConstructor
public class QuestionController {

    private final QuestionService questionService;

    @GetMapping("/{id}")
    public ResponseEntity<QuestionDto> findQuestionById(@PathVariable Integer id) throws QuestionNotFound {
        return ResponseEntity.ok(questionService.findQuestionById(id));
    }

    @GetMapping(params = {"category"})
    public ResponseEntity<List<QuestionDto>> findQuestionsByCategory(
            String category, @RequestParam(defaultValue = "3", required = false) Integer numOfQuestions) {
        return ResponseEntity.ok(questionService.findQuestionsByCategory(category, numOfQuestions));
    }

    @GetMapping(params = {"category", "difficulty"})
    public ResponseEntity<List<QuestionDto>> findQuestionsByCategoryAndDifficulty(
            String category, Difficulty difficulty,
            @RequestParam(defaultValue = "3", required = false) Integer numOfQuestions) {
        return ResponseEntity.ok(questionService.findQuestionsByCategoryAndDifficulty(category, difficulty.name(), numOfQuestions));
    }
}
