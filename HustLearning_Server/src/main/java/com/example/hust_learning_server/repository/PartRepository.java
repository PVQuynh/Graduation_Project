package com.example.hust_learning_server.repository;

import com.example.hust_learning_server.entity.Lesson;
import com.example.hust_learning_server.entity.Part;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface PartRepository extends JpaRepository<Part, Long> {

    List<Part> findAllByLessonId(long lessonId);
}
