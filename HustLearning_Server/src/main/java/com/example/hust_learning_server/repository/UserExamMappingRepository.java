package com.example.hust_learning_server.repository;

import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.hust_learning_server.entity.UserExamMapping;


public interface UserExamMappingRepository extends JpaRepository<UserExamMapping, Long> {

    Page<UserExamMapping> findAllByUserId(long userId, Pageable pageable);

    Optional<UserExamMapping> findByUserIdAndExamId(long userId, long examId);

    boolean existsByUserIdAndExamId(long userId, long examId);
}
