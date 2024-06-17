package com.example.hust_learning_server.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.example.hust_learning_server.entity.Exam;
import com.example.hust_learning_server.entity.QuestionExamMapping;
import feign.Param;


public interface QuestionExamMappingRepository extends JpaRepository<QuestionExamMapping, Long> {

    List<QuestionExamMapping> findAllByExamId(Long examId);

    List<QuestionExamMapping> findAllByQuestionId(Long questionId);
}
