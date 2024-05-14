package com.example.hust_learning_server.repository;

import com.example.hust_learning_server.entity.Answer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AnswerRepository extends JpaRepository<Answer, Long> {

    List<Answer> findAllByQuestionId(long questionId);
}
