package com.example.hust_learning_server.service;

import com.example.hust_learning_server.dto.request.PartImageReq;
import com.example.hust_learning_server.dto.request.UpdatePartImageReq;
import com.example.hust_learning_server.dto.response.PartImageRes;

import java.util.List;

public interface PartImageService {
    void updatePartImage(UpdatePartImageReq updatePartImageReq);

    void addPartImage(PartImageReq partImageReq);

    void addPartImages(List<PartImageReq> partImageReqs);

    PartImageRes getPartImage(long partImageId);

    void deleteById(long id);

}
