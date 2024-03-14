package com.example.learning_server.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = "question")
@Table(name = "answer")
@Entity
@AttributeOverride(name = "id", column = @Column(name = "answer_id"))
public class Answer extends BaseEntity {

    private String content;

    private String imageLocation;

    private String videoLocation;

    private boolean isCorrect;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "question_id")
    private Question question;

}
