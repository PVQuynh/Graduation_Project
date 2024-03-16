package com.example.learning_server.service;


import com.example.learning_server.dto.PageDTO;
import com.example.learning_server.dto.request.*;
import com.example.learning_server.dto.response.DataCollectionRes;
import com.example.learning_server.dto.response.SearchDataRes;

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
