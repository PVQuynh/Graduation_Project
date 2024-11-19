package com.example.hust_learning_server.repository;

import com.example.hust_learning_server.constant.sql.SQLVocabulary;
import com.example.hust_learning_server.entity.Vocabulary;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface VocabularyRepository extends JpaRepository<Vocabulary, Long> {

    List<Vocabulary> findAllByContent(String content);

    @Query(nativeQuery = true, value = SQLVocabulary.GET_ALL_VOCABULARIES)
    List<Vocabulary> findAllVocabularies(long topicId, String vocabularyType, int isPrivate, String email,String contentSearch);

    @Query("select v from Vocabulary v where v.topic.id = :topicId order by v.content asc")
    List<Vocabulary> findVocabulariesByTopicId(@Param("topicId") long topicId);

    @Query("select v from Vocabulary v where v.topic.id = :topicId order by v.content asc")
    List<Vocabulary> findVocabulariesLimitByTopicId(@Param("topicId") long topicId, Pageable pageable);

    Optional<Vocabulary> findByContentAndTopicId(String content, long topicId);

    Optional<Vocabulary> findByContentAndLessonIdAndPartId(String content, long lessonId, long partId);

    List<Vocabulary> findAllByTopicId(long id);

    List<Vocabulary> findAllByLessonIdAndPartId(long lessonId, long partId);
}
