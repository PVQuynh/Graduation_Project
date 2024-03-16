package com.example.hust_learning_server.repository;

import com.example.hust_learning_server.entity.Answer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface AnswerRepository extends JpaRepository<Answer, Long> {

    // get answers by questionId
    @Query("select a from Answer a where a.question.id = :questionId")
    Optional<List<Answer>> findAnswersByQuestionId(@Param("questionId") long questionId);
}
