package com.example.HustLearning.repository;

import com.example.HustLearning.entity.Vocabulary;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface VocabularyRepository extends JpaRepository<Vocabulary, Long> {

    @Query("select v from Vocabulary v where v.topic.id = :topicId order by v.content desc")
    Optional<List<Vocabulary>> findVocabulariesByTopicId(@Param("topicId") long topicId);

    @Query("select v from Vocabulary v where v.topic.id = :topicId order by v.content desc")
    Optional<List<Vocabulary>> findVocabulariesLimitByTopicId(@Param("topicId") long topicId, Pageable pageable);

    Optional<Vocabulary> findByContent(String content);
}
