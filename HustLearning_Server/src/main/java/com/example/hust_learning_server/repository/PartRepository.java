package com.example.hust_learning_server.repository;

import com.example.hust_learning_server.entity.Part;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PartRepository extends JpaRepository<Part, Long> {

    @NotNull
    @Query(value = "SELECT * FROM part ORDER BY CAST(SUBSTRING_INDEX(part_name, ' ', -1) AS UNSIGNED) DESC", nativeQuery = true)
    List<Part> findAll();

    @Query(value = "SELECT * FROM part WHERE lesson_id = :lessonId ORDER BY CAST(SUBSTRING_INDEX(part_name, ' ', -1) AS UNSIGNED) DESC", nativeQuery = true)
    List<Part> findAllByLessonId(long lessonId);

    boolean existsByPartNameAndLessonId(String partName, long lessonId);
}
