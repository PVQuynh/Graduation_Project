package com.example.hust_learning_server.service;


import com.example.hust_learning_server.dto.PageDTO;
import com.example.hust_learning_server.dto.request.*;
import com.example.hust_learning_server.dto.response.DataCollectionRes;
import com.example.hust_learning_server.dto.response.SearchDataRes;

import java.text.ParseException;
import java.util.List;

public interface DataCollectionService {

    // Me
    List<DataCollectionRes> getAllMe();

    List<DataCollectionRes> getOptionsListMe(int status);

    List<DataCollectionRes> getPendingMe();

    List<DataCollectionRes> getApprovedMe();

    List<DataCollectionRes> getRejectMe();

    PageDTO<SearchDataRes> searchDataCollectionForUser(DataSearchForUserParam dataSearchForUserParam)
            throws ParseException;

    PageDTO<SearchDataRes> searchDataCollectionForUser_v2(int page, int size, String topic, String vocabulary, boolean ascending, String orderBy, String createdFrom, String createdTo, int status, float score) throws ParseException;

    PageDTO<SearchDataRes> searchDataCollectionForUserV3(DataSearchForUserParamV3 dataSearchForUserParam)
            throws ParseException;

    void sendData(DataProvideReq dataProvideReq);

    void updateData(UpdateDataReq updateDataReq);

    // Admin
    List<DataCollectionRes> getOptionsListAdmin(int status);

    List<DataCollectionRes> getPendingAdmin();

    PageDTO<SearchDataRes> searchDataCollectionForAdmin(DataSearchForAdminParam dataSearchForAdminParam)
            throws ParseException;

    PageDTO<SearchDataRes> searchDataCollectionForAdmin_v2(int page, int size, String volunteerEmail, String topic, String vocabulary, boolean ascending, String orderBy, String createdFrom, String createdTo, int status, float score) throws ParseException;

    void approve(DataReq dataReq);

    void reject(DataReq dataReq);

    void delete(long id);
}
