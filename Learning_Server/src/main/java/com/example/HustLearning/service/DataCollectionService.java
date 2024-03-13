package com.example.HustLearning.service;


import com.example.HustLearning.dto.PageDTO;
import com.example.HustLearning.dto.request.*;
import com.example.HustLearning.dto.response.DataCollectionRes;
import com.example.HustLearning.dto.response.SearchDataRes;

import java.text.ParseException;
import java.util.List;

public interface DataCollectionService {

    // Me
    List<DataCollectionRes> getAllMe();

    List<DataCollectionRes> getPendingMe();

    List<DataCollectionRes> getApprovedMe();

    List<DataCollectionRes> getRejectMe();

    PageDTO<SearchDataRes> searchDataCollectionForUser(DataSearchForUserParam dataSearchForUserParam)
            throws ParseException;

    void sendData(DataProvideReq dataProvideReq);

    void updateData(UpdateDataReq updateDataReq);

    // Admin
    List<DataCollectionRes> getPendingAdmin();

    PageDTO<SearchDataRes> searchDataCollectionForAdmin(DataSearchForAdminParam dataSearchForAdminParam)
            throws ParseException;

    void approve(long dataCollectionId);

    void reject(DataRejectReq dataRejectReq);

    void delete(long id);

}
