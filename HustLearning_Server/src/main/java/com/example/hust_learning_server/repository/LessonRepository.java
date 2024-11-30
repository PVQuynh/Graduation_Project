package com.example.hust_learning_server.repository;

import com.example.hust_learning_server.entity.Lesson;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LessonRepository extends JpaRepository<Lesson, Long> {

    @Query(value = "SELECT * " +
            "FROM lesson " +
            "WHERE class_room_id = :classRoomId " +
            "ORDER BY " +
            "    SUBSTRING_INDEX(lesson_name, ' ', 1), " +
            "    SUBSTRING_INDEX(SUBSTRING_INDEX(lesson_name, ' ', 2), ' ', -1), " +
            "    SUBSTRING_INDEX(SUBSTRING_INDEX(lesson_name, ' ', 3), ' ', -1), " +
            "    SUBSTRING_INDEX(SUBSTRING_INDEX(lesson_name, ' ', 4), ' ', -1), " +
            "    SUBSTRING_INDEX(SUBSTRING_INDEX(lesson_name, ' ', 5), ' ', -1), " +
            "    SUBSTRING_INDEX(SUBSTRING_INDEX(lesson_name, ' ', 6), ' ', -1), " +
            "    SUBSTRING_INDEX(SUBSTRING_INDEX(lesson_name, ' ', 7), ' ', -1), " +
            "    SUBSTRING_INDEX(SUBSTRING_INDEX(lesson_name, ' ', 8), ' ', -1), " +
            "    SUBSTRING_INDEX(SUBSTRING_INDEX(lesson_name, ' ', 9), ' ', -1), " +
            "    SUBSTRING_INDEX(SUBSTRING_INDEX(lesson_name, ' ', 10), ' ', -1), " +
            "    SUBSTRING_INDEX(lesson_name, ' ', -1) ",
            nativeQuery = true)
    List<Lesson> findAllByClassRoomId(long classRoomId);

    @NotNull
    @Query(value = "SELECT * " +
            "FROM lesson " +
            "ORDER BY " +
            "    SUBSTRING_INDEX(lesson_name, ' ', 1), " +
            "    SUBSTRING_INDEX(SUBSTRING_INDEX(lesson_name, ' ', 2), ' ', -1), " +
            "    SUBSTRING_INDEX(SUBSTRING_INDEX(lesson_name, ' ', 3), ' ', -1), " +
            "    SUBSTRING_INDEX(SUBSTRING_INDEX(lesson_name, ' ', 4), ' ', -1), " +
            "    SUBSTRING_INDEX(SUBSTRING_INDEX(lesson_name, ' ', 5), ' ', -1), " +
            "    SUBSTRING_INDEX(SUBSTRING_INDEX(lesson_name, ' ', 6), ' ', -1), " +
            "    SUBSTRING_INDEX(SUBSTRING_INDEX(lesson_name, ' ', 7), ' ', -1), " +
            "    SUBSTRING_INDEX(SUBSTRING_INDEX(lesson_name, ' ', 8), ' ', -1), " +
            "    SUBSTRING_INDEX(SUBSTRING_INDEX(lesson_name, ' ', 9), ' ', -1), " +
            "    SUBSTRING_INDEX(SUBSTRING_INDEX(lesson_name, ' ', 10), ' ', -1), " +
            "    SUBSTRING_INDEX(lesson_name, ' ', -1) ",
            nativeQuery = true)
    List<Lesson> findAll();
}
