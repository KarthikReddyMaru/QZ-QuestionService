package com.qz.questionservice.config;

import com.qz.questionservice.constants.Category;
import com.qz.questionservice.constants.Difficulty;
import com.qz.questionservice.model.Question;
import com.qz.questionservice.repo.QuestionRepo;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class BootConfig {

    @Bean
    CommandLineRunner commandLineRunner(QuestionRepo questionRepo) {
        return (args) -> {
            for(int i = 1; i <= 15; i++) {
                Question q = new Question();
                q.setQuestion("Question "+i);
                q.setOptions(List.of("Option 1","Option 2","Option 3","Option 4"));
                q.setCorrectAnswer("Option 3");
                q.setCategory(Category.Java);
                double random = Math.random();
                if(random < 0.4)
                    q.setDifficulty(Difficulty.EASY);
                else if(random < 0.8)
                    q.setDifficulty(Difficulty.MEDIUM);
                else
                    q.setDifficulty(Difficulty.HARD);
                questionRepo.save(q);
            }
        };
    }
}
