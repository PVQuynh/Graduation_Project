package com.example.hust_learning_server.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.example.hust_learning_server.entity.Exam;
import feign.Param;


public interface ExamRepository extends JpaRepository<Exam, Long> {
    @Query("select exam from Exam exam where exam.topic.id = :topicId and exam.isPrivate = :isPrivate and exam.author = :email")
    List<Exam> findAllByTopicIdAndPrivateAndAuthor(@Param("topicId") long topicId, @Param("isPrivate") boolean isPrivate, @Param("email") String email);

    @Query("select exam from Exam exam where exam.topic.id = :topicId and exam.isPrivate = :isPrivate")
    List<Exam> findAllByTopicIdAndPrivate(@Param("topicId") long topicId, @Param("isPrivate") boolean isPrivate);
}
