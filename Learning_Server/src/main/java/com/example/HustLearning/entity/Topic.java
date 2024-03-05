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
@Table(name = "topic")
@ToString(exclude = {"vocabularies", "questions"})
@Entity
@AttributeOverride(name = "id", column = @Column(name = "topic_id"))
public class Topic extends BaseEntity{

    @Column(name = "content")
    private String content;

    @Column(name = "image_location",length = 2000)
    private String imageLocation;

    @Column(name = "video_location",length = 2000)
    private  String videoLocation;

    @JsonIgnore
    @OneToMany(mappedBy = "topic",
            fetch = FetchType.LAZY,
            cascade = {CascadeType.PERSIST, CascadeType.MERGE,
                    CascadeType.DETACH, CascadeType.REFRESH})
    private List<Vocabulary> vocabularies;

    @JsonIgnore
    @OneToMany(mappedBy = "topic",
            fetch = FetchType.LAZY,
            cascade = {CascadeType.PERSIST, CascadeType.MERGE,
                    CascadeType.DETACH, CascadeType.REFRESH})
    private List<Question> questions;

}
