package com.example.hust_learning_server.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "vocabulary")
@Entity
@AttributeOverride(name = "id", column = @Column(name = "vocabulary_id"))
public class Vocabulary extends BaseEntity{

    private String content;

    @OneToMany(
            mappedBy = "vocabulary",
            fetch = FetchType.EAGER,
            cascade = {CascadeType.ALL})
    private List<VocabularyMedium> vocabularyMedia;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="topic_id")
    private Topic topic;
}
