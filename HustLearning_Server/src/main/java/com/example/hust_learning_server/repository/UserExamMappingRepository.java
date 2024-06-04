package com.example.hust_learning_server.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.hust_learning_server.entity.UserExamMapping;


public interface UserExamMappingRepository extends JpaRepository<UserExamMapping, Long> {
    List<UserExamMapping> findAllByUserId(long userId);
}
