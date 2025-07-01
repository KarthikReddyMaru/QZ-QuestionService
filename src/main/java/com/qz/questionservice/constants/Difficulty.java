package com.qz.questionservice.constants;

import lombok.Getter;

@Getter
public enum Difficulty {
    EASY(1), MEDIUM(3), HARD(5);

    private final int score;
    Difficulty(int score) {
        this.score = score;
    }

}
