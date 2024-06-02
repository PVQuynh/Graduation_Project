package com.example.hust_learning_server.repository;

import com.example.hust_learning_server.entity.Question;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface QuestionRepository extends JpaRepository<Question, Long> {

    @Query("select q from Question q where q.topic.id = :topicId")
    List<Question>findQuestionsByTopicId(@Param("topicId") long topicId );

    @Query("select q from Question q where q.topic.id = :topicId")
    List<Question> findQuestionLimitsByTopicId(@Param("topicId") long topicId, Pageable pageable);

    @Query("select q from Question q where q.topic.id = :topicId")
    List<Question>searchQuestionsByTopicId(@Param("topicId") long topicId, Pageable pageable);

    List<Question> findAllByTopicId(long id);
}
