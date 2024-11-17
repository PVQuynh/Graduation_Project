package com.example.hust_learning_server.service;

import com.example.hust_learning_server.dto.request.PartReq;
import com.example.hust_learning_server.dto.response.PartRes;

import java.util.List;

public interface PartService {

    void add(PartReq partReq);

    PartRes getById(Long partId);
    
    List<PartRes> getAll(Long lessonId);

    void update(PartRes partRes);

    void deleteById(Long partId);
}
