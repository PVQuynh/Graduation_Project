package com.example.hust_learning_server.service;

import com.example.hust_learning_server.dto.request.PartReq;
import com.example.hust_learning_server.dto.request.UpdatePartReq;
import com.example.hust_learning_server.dto.response.PartRes;

import java.util.List;

public interface PartService {
    void addPart(PartReq partReq);

    void addParts(List<PartReq> partReqList);

    PartRes getPart(long partId);

    List<PartRes> getAllParts(long lessonId);

    void deletePart(long partId);

    void updatePart(UpdatePartReq updatePartReq);

}
