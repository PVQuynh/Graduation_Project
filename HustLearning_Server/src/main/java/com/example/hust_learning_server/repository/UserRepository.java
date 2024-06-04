package com.example.hust_learning_server.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.hust_learning_server.entity.User;


public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
}
