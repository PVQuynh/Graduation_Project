package com.example.HustLearning.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
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

    @Column(name = "content")
    private String content;

    @Column(name = "image_location",length = 2000)
    private String imageLocation;

    @Column(name = "video_location",length = 2000)
    private String videoLocation;

    @Column(name = "is_correct")
    private boolean isCorrect;

    @JsonIgnore
    @ManyToOne(cascade = {CascadeType.PERSIST},fetch = FetchType.EAGER)
    @JoinColumn(name = "question_id")
    private Question question;

}
