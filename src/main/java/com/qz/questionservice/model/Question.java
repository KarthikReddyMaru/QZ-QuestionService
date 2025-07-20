package com.qz.questionservice.model;

import com.qz.questionservice.constants.Category;
import com.qz.questionservice.constants.Difficulty;
import com.qz.questionservice.convertor.ListToStringConvertor;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
@Table(indexes = {
        @Index(name = "category_idx", columnList = "category"),
        @Index(name = "difficulty_idx", columnList = "difficulty")
})
public class Question extends Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String question;

    @Column(nullable = false)
    @Convert(converter = ListToStringConvertor.class)
    @Lob
    private List<String> options;

    @Column(nullable = false)
    private String correctAnswer;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Category category;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Difficulty difficulty;
}
