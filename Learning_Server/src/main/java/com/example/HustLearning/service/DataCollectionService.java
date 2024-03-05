package com.example.HustLearning.service;


import com.example.HustLearning.dto.PageDTO;
import com.example.HustLearning.dto.request.DataProvideReq;
import com.example.HustLearning.dto.request.DataRejectReq;
import com.example.HustLearning.dto.request.DataSearchparam;
import com.example.HustLearning.dto.response.DataCollectionRes;
import com.example.HustLearning.dto.response.SearchDataRes;
import com.example.HustLearning.entity.DataCollection;
import java.text.ParseException;
import java.util.List;

public interface DataCollectionService {
    void sendData(DataProvideReq dataProvideReq);

    void approve(long dataCollectionId);

    void reject(DataRejectReq dataRejectReq);

    List<DataCollection> getHistory();

    PageDTO<SearchDataRes> getApproved(DataSearchparam dataSearchparam) throws ParseException;

    List<DataCollectionRes> getPending();

    PageDTO<SearchDataRes> searchDataCollection(DataSearchparam dataSearchparam)
        throws ParseException;
}
