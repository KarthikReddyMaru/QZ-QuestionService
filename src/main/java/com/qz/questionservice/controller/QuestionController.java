package com.qz.questionservice.controller;

import com.qz.questionservice.constants.Category;
import com.qz.questionservice.constants.Difficulty;
import com.qz.questionservice.dto.QuestionDto;
import com.qz.questionservice.dto.QuestionResponseDto;
import com.qz.questionservice.exception.QuestionNotFound;
import com.qz.questionservice.service.QuestionService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@AllArgsConstructor
public class QuestionController {

    private final QuestionService questionService;

    @GetMapping("/{id}")
    public ResponseEntity<QuestionDto> findQuestionById(@PathVariable Integer id) throws QuestionNotFound {
        return ResponseEntity.ok(questionService.findQuestionById(id));
    }

    @GetMapping(params = {"category"})
    public ResponseEntity<List<QuestionDto>> findQuestionsByCategory(
            Category category, @RequestParam(defaultValue = "3", required = false) Integer numOfQuestions) {
        return ResponseEntity.ok(questionService.findQuestionsByCategory(category.name(), numOfQuestions));
    }

    @GetMapping(params = {"category", "difficulty"})
    public ResponseEntity<List<QuestionDto>> findQuestionsByCategoryAndDifficulty(
            String category, Difficulty difficulty,
            @RequestParam(defaultValue = "3", required = false) Integer numOfQuestions) {
        return ResponseEntity.ok(questionService.findQuestionsByCategoryAndDifficulty(category, difficulty.name(), numOfQuestions));
    }

    @PostMapping("/id")
    public ResponseEntity<List<QuestionDto>> findQuestionsByIds(@RequestBody List<Integer> questionIds) {
        return ResponseEntity.ok(questionService.findQuestionsByIds(questionIds));
    }

    @PostMapping
    public ResponseEntity<String> saveQuestion(@RequestBody QuestionDto questionDto) {
        int id = questionService.saveQuestion(questionDto);
        log.info("Question is created with id: {}", id);
        return new ResponseEntity<>("Question is created with id: " + id, HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<String> updateQuestion(@RequestBody QuestionDto questionDto) {
        int id = questionService.updateQuestion(questionDto);
        log.info("Question with ID {} is updated", id);
        return new ResponseEntity<>(String.format("Question with ID %d is updated", id), HttpStatus.CREATED);
    }

    @GetMapping(params = {"category", "numberOfQuestions"}, path = "/id")
    public ResponseEntity<List<Integer>> findQuestionIdsByCategory(Category category, int numberOfQuestions) {
        return ResponseEntity.ok(questionService.findQuestionIdsByCategory(category.name(), numberOfQuestions));
    }

    @GetMapping(params = {"numberOfQuestions, category, difficulty"}, path = "/id")
    public ResponseEntity<List<Integer>> findQuestionIdsByCategoryAndDifficulty(
            Category category, int numberOfQuestions, Difficulty difficulty) {
        return ResponseEntity.ok(questionService.findQuestionIdsByCategoryAndDifficulty(category, numberOfQuestions, difficulty));
    }

    @PostMapping("/calculate")
    public ResponseEntity<Integer> calculateScore(@RequestBody List<QuestionResponseDto> responses) {
        int totalScore = questionService.calculateScore(responses);
        log.info("Total score {}", totalScore);
        return ResponseEntity.ok(totalScore);
    }
}
