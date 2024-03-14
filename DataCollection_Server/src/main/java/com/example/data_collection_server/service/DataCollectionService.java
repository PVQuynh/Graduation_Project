package com.example.data_collection_server.service;

import com.example.data_collection_server.dto.request.DataProvideReq;
import com.example.data_collection_server.dto.request.DataRejectReq;
import com.example.data_collection_server.entity.DataCollection;

import java.util.List;

public interface DataCollectionService {
    void sendData(DataProvideReq dataProvideReq);

    void approve(long dataCollectionId);

    void reject(DataRejectReq dataRejectReq);

    List<DataCollection> getHistory();

    List<DataCollection> getApproved();

    List<DataCollection> getPending();
}
