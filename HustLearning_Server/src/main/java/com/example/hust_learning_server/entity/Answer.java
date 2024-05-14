package com.example.hust_learning_server.entity;

import jakarta.persistence.*;
import lombok.*;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "answer")
@Entity
@AttributeOverride(name = "id", column = @Column(name = "answer_id"))
public class Answer extends BaseEntity {

    private String content;

    private String imageLocation;

    private String videoLocation;

    private boolean isCorrect;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "question_id")
    private Question question;

}
