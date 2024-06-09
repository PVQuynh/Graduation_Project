package com.example.hust_learning_server.repository;

import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.example.hust_learning_server.constant.sql.SQLExam;
import com.example.hust_learning_server.entity.Exam;


public interface ExamRepository extends JpaRepository<Exam, Long> {
    @Query(nativeQuery = true, value = SQLExam.GET_ALL_TOPIC)
    Page<Exam> findAllExam(long topicId, int isPrivate, String email, String nameSearch, Pageable pageable);

}
