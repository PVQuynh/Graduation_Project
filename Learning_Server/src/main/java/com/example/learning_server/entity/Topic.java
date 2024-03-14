package com.example.learning_server.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "topic")
@Entity
@AttributeOverride(name = "id", column = @Column(name = "topic_id"))
@Builder
public class Topic extends BaseEntity{

    private String content;

    private String imageLocation;

    private  String videoLocation;

    @OneToMany(mappedBy = "topic",
            fetch = FetchType.LAZY,
            cascade = {CascadeType.ALL})
    private List<Vocabulary> vocabularies;

    @OneToMany(mappedBy = "topic",
            fetch = FetchType.LAZY,
            cascade = {CascadeType.ALL})
    private List<Question> questions;

}
