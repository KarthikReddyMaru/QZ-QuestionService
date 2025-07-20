package com.qz.questionservice.repo;

import com.qz.questionservice.constants.Difficulty;
import com.qz.questionservice.model.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionRepo extends JpaRepository<Question, Integer> {

    @Query(value = "select * from Question where category = ?1 order by rand() limit ?2", nativeQuery = true)
    List<Question> findQuestionsByCategory(String category, int numberOfQuestions);

    @Query(value = "select * from Question where category = ?1 and difficulty = ?2 order by rand() limit ?3", nativeQuery = true)
    List<Question> findQuestionsByCategoryAndDifficulty(String category, String difficulty, int numberOfQuestions);
}
