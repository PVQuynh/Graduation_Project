package com.example.hust_learning_server.repository;

import com.example.hust_learning_server.entity.PartVideo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PartVideoRepository extends JpaRepository<PartVideo, Long> {


    List<PartVideo> findByPartId(long id);
}
