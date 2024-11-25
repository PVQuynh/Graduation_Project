package com.example.hust_learning_server.repository;

import com.example.hust_learning_server.entity.PartImage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PartImageRepository extends JpaRepository<PartImage, Long> {


    List<PartImage> findByPartId(long id);

    boolean existsByImageLocationAndPartId(String imageLocation, long partId);
}
