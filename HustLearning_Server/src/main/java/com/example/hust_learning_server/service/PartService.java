package com.example.hust_learning_server.service;

import com.example.hust_learning_server.dto.request.PartReq;
import com.example.hust_learning_server.dto.response.PartRes;

import java.util.List;

public interface PartService {
    void addPart(PartReq partReq);

    void addParts(List<PartReq> partReqList);

    List<PartRes> getAllParts(long lessonId);
}
