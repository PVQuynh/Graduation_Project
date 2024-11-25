package com.example.hust_learning_server.service;

import com.example.hust_learning_server.dto.request.PartImageReq;
import com.example.hust_learning_server.dto.request.UpdatePartImageReq;

public interface PartImageService {
    void updatePartImage(UpdatePartImageReq updatePartImageReq);

    void addPartImage(PartImageReq partImageReq);

    void deleteById(long id);
}
