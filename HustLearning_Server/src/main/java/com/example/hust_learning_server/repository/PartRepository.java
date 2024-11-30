package com.example.hust_learning_server.repository;

import com.example.hust_learning_server.entity.Part;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PartRepository extends JpaRepository<Part, Long> {

    @NotNull
    @Query(value = "SELECT * " +
            "FROM part " +
            "ORDER BY " +
            "    SUBSTRING_INDEX(part_name, ' ', 1), " +
            "    SUBSTRING_INDEX(SUBSTRING_INDEX(part_name, ' ', 2), ' ', -1), " +
            "    SUBSTRING_INDEX(SUBSTRING_INDEX(part_name, ' ', 3), ' ', -1), " +
            "    SUBSTRING_INDEX(SUBSTRING_INDEX(part_name, ' ', 4), ' ', -1), " +
            "    SUBSTRING_INDEX(SUBSTRING_INDEX(part_name, ' ', 5), ' ', -1), " +
            "    SUBSTRING_INDEX(SUBSTRING_INDEX(part_name, ' ', 6), ' ', -1), " +
            "    SUBSTRING_INDEX(SUBSTRING_INDEX(part_name, ' ', 7), ' ', -1), " +
            "    SUBSTRING_INDEX(SUBSTRING_INDEX(part_name, ' ', 8), ' ', -1), " +
            "    SUBSTRING_INDEX(SUBSTRING_INDEX(part_name, ' ', 9), ' ', -1), " +
            "    SUBSTRING_INDEX(SUBSTRING_INDEX(part_name, ' ', 10), ' ', -1), " +
            "    SUBSTRING_INDEX(part_name, ' ', -1) ",
            nativeQuery = true)
    List<Part> findAll();

    @Query(value = "SELECT * " +
            "FROM part " +
            "WHERE lesson_id = :lessonId " +
            "ORDER BY " +
            "    SUBSTRING_INDEX(part_name, ' ', 1), " +
            "    SUBSTRING_INDEX(SUBSTRING_INDEX(part_name, ' ', 2), ' ', -1), " +
            "    SUBSTRING_INDEX(SUBSTRING_INDEX(part_name, ' ', 3), ' ', -1), " +
            "    SUBSTRING_INDEX(SUBSTRING_INDEX(part_name, ' ', 4), ' ', -1), " +
            "    SUBSTRING_INDEX(SUBSTRING_INDEX(part_name, ' ', 5), ' ', -1), " +
            "    SUBSTRING_INDEX(SUBSTRING_INDEX(part_name, ' ', 6), ' ', -1), " +
            "    SUBSTRING_INDEX(SUBSTRING_INDEX(part_name, ' ', 7), ' ', -1), " +
            "    SUBSTRING_INDEX(SUBSTRING_INDEX(part_name, ' ', 8), ' ', -1), " +
            "    SUBSTRING_INDEX(SUBSTRING_INDEX(part_name, ' ', 9), ' ', -1), " +
            "    SUBSTRING_INDEX(SUBSTRING_INDEX(part_name, ' ', 10), ' ', -1), " +
            "    SUBSTRING_INDEX(part_name, ' ', -1) ",
            nativeQuery = true)
    List<Part> findAllByLessonId(long lessonId);

    boolean existsByPartNameAndLessonId(String partName, long lessonId);
}
