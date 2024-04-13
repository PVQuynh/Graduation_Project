package com.example.hust_learning_server.repository;

import com.example.hust_learning_server.entity.VocabularyVideo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface VocabularyVideoRepository extends JpaRepository<VocabularyVideo, Long> {

    List<VocabularyVideo> findAllByVocabularyId(long vocabularyId);
}
