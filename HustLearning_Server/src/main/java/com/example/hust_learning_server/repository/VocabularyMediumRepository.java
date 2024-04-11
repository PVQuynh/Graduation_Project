package com.example.hust_learning_server.repository;

import com.example.hust_learning_server.entity.VocabularyMedium;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface VocabularyMediumRepository extends JpaRepository<VocabularyMedium, Long> {

    List<VocabularyMedium> findAllByVocabularyId(long vocabularyId);
}
