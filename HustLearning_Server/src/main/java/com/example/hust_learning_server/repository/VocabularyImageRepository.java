package com.example.hust_learning_server.repository;

import com.example.hust_learning_server.entity.VocabularyImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;


public interface VocabularyImageRepository extends JpaRepository<VocabularyImage, Long> {

    List<VocabularyImage> findAllByVocabularyId(long vocabularyId);

    Optional<VocabularyImage> findByImageLocationAndVocabularyId(String imageLocation, long vocabularyId);

    @Modifying
    @Transactional
    void deleteAllByVocabularyId(long vocabularyId);
}
