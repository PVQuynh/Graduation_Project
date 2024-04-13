package com.example.hust_learning_server.entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "vocabulary_image")
@Entity
@AttributeOverride(name = "id", column = @Column(name = "vocabulary_image_id"))
public class VocabularyImage extends BaseEntity{

    private String imageLocation;

    private boolean isPrimary;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="vocabulary_id")
    private Vocabulary vocabulary;
}
