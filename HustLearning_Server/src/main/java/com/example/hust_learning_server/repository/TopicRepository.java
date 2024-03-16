package com.example.hust_learning_server.repository;

import com.example.hust_learning_server.entity.Topic;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TopicRepository extends JpaRepository<Topic, Long> {

    Optional<Topic> findByContent(String content);

}
