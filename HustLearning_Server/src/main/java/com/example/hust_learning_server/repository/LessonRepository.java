package com.example.hust_learning_server.repository;

import com.example.hust_learning_server.entity.Lesson;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LessonRepository extends JpaRepository<Lesson, Long> {

    List<Lesson> findAllByClassRoomId(long classRoomId);
}
