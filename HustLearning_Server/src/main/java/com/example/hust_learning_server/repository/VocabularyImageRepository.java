package com.example.hust_learning_server.repository;

import com.example.hust_learning_server.entity.VocabularyImage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;


public interface VocabularyImageRepository extends JpaRepository<VocabularyImage, Long> {

    List<VocabularyImage> findAllByVocabularyId(long vocabularyId);

    Optional<VocabularyImage> findByImageLocationAndVocabularyId(String imageLocation, long vocabularyId);
}
