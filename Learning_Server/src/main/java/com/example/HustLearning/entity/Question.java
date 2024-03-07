package com.example.HustLearning.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "question")
@ToString(exclude = {"topic", "answers"})
@Entity
@AttributeOverride(name = "id", column = @Column(name = "question_id"))
public class Question extends BaseEntity {

    @Column(name = "content")
    private String content;

    @Column(name = "explanation",length = 2000)
    private String explanation;

    @Column(name = "image_location",length = 2000)
    private String imageLocation;

    @Column(name = "video_location",length = 2000)
    private String videoLocation;

    @OneToMany(mappedBy = "question",
            cascade = {CascadeType.ALL},
            fetch = FetchType.EAGER)
    private List<Answer> answers;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "topic_id")
    private Topic topic;

}
