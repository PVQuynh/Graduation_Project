package com.example.hust_learning_server.service;

import com.example.hust_learning_server.dto.request.PartVideoReq;
import com.example.hust_learning_server.dto.request.UpdatePartVideoReq;
import com.example.hust_learning_server.dto.response.PartVideoRes;

import java.util.List;

public interface PartVideoService {
    void updatePartVideo(UpdatePartVideoReq updatePartVideoReq);

    void addPartVideo(PartVideoReq partVideoReq);

    void deleteById(long id);

    PartVideoRes getPartVideo(long partVideoId);

    void addPartVideos(List<PartVideoReq> partVideoReqs);
}
