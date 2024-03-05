package com.example.HustLearning.repository;

import com.example.HustLearning.entity.Answer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AnswerRepository extends JpaRepository<Answer, Long> {

    // get answers by questionId
    @Query("select a from Answer a where a.question.id = :questionId")
    public List<Answer> findAnswersByQuestionId(@Param("questionId") long questionId);
}
