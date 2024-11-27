package com.example.hust_learning_server.service;

import com.example.hust_learning_server.dto.request.PartVideoReq;
import com.example.hust_learning_server.dto.request.UpdatePartVideoReq;

public interface PartVideoService {
    void updatePartVideo(UpdatePartVideoReq updatePartVideoReq);

    void addPartVideo(PartVideoReq partVideoReq);

    void deleteById(long id);
}
