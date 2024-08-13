package com.example.hust_learning_server.service;

import com.example.hust_learning_server.dto.request.IntroductionReq;
import com.example.hust_learning_server.dto.response.IntroductionRes;

public interface IntroductionService {

    void addIntroduction (IntroductionReq introductionReq);

    void updateIntroduction (long introductionId, IntroductionReq introductionReq);

    IntroductionRes getIntroduction(long introductionId);

}
