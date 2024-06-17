package com.example.hust_learning_server.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.hust_learning_server.entity.QuestionExamUserMapping;


public interface QuestionExamUserMappingRepository extends JpaRepository<QuestionExamUserMapping, Long> {

    List<QuestionExamUserMapping> findByExamIdAndUserId(Long examId, Long userId);

    List<QuestionExamUserMapping> findAllByQuestionId(Long questionId);
}
