package com.example.hust_learning_server.entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "vocabulary_video")
@Entity
@AttributeOverride(name = "id", column = @Column(name = "vocabulary_video_id"))
public class VocabularyVideo extends BaseEntity{

    private String videoLocation;

    private boolean isPrimary;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="vocabulary_id")
    private Vocabulary vocabulary;
}
