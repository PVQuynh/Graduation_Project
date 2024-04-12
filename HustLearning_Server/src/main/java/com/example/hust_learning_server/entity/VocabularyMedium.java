package com.example.hust_learning_server.entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "vocabulary_medium")
@Entity
@AttributeOverride(name = "id", column = @Column(name = "vocabulary_medium_id"))
public class VocabularyMedium extends BaseEntity{

    private String imageLocation;

    private String videoLocation;

    private boolean isPrimary;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="vocabulary_id")
    private Vocabulary vocabulary;
}
