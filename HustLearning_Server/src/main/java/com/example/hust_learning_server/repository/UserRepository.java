package com.example.hust_learning_server.repository;

import java.util.Optional;

import com.example.hust_learning_server.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
}
