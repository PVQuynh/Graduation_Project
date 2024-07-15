package com.example.hust_learning_server.service;

import com.example.hust_learning_server.dto.request.MobileReq;
import com.example.hust_learning_server.dto.response.MobileRes;

import java.util.List;

public interface MobileService {

    void addLinkMobile (MobileReq mobileReq);

    void updateLinkMobile (long mobileId, MobileReq mobileReq);

    List<MobileRes> getAllLinkMobile ();

    MobileRes getLinkMobile(long mobileId);

    void deleteLinkMobile(long mobileId);

}
