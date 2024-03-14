package com.example.learning_server.repository;

import com.example.learning_server.entity.Question;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface QuestionRepository extends JpaRepository<Question, Long> {

    Question findQuestionsByContent(String content);

    @Query("select q from Question q where q.topic.id = :topicId")
    Optional<List<Question>>findQuestionsByTopicId(@Param("topicId") long topicId );

    @Query("select q from Question q where q.topic.id = :topicId")
    Optional<List<Question>> findQuestionLimitsByTopicId(@Param("topicId") long topicId, Pageable pageable);

    @Query("select q from Question q where q.topic.id = :topicId")
    Optional<List<Question>>searchQuestionsByTopicId(@Param("topicId") long topicId, Pageable pageable);
}
