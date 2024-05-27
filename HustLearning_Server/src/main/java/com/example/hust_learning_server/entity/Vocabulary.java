package com.example.hust_learning_server.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "vocabulary")
@Entity
@AttributeOverride(name = "id", column = @Column(name = "vocabulary_id"))
public class Vocabulary extends BaseEntity{

    private String content;

    private String note;

    @OneToMany(
            mappedBy = "vocabulary",
            fetch = FetchType.LAZY,
            cascade = {CascadeType.ALL})
    private List<VocabularyImage> vocabularyImages;

    @OneToMany(
            mappedBy = "vocabulary",
            fetch = FetchType.LAZY,
            cascade = {CascadeType.ALL})
    private List<VocabularyVideo> vocabularyVideos;

    @OneToMany(
            mappedBy = "vocabulary",
            fetch = FetchType.LAZY,
            cascade = {CascadeType.ALL})
    private List<DataCollection> dataCollections;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="topic_id")
    private Topic topic;
}
