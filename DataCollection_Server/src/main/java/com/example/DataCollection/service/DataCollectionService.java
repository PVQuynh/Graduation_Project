package com.example.DataCollection.service;

import com.example.DataCollection.dto.request.DataProvideReq;
import com.example.DataCollection.dto.request.DataRejectReq;
import com.example.DataCollection.entity.DataCollection;

import java.util.List;

public interface DataCollectionService {
    void sendData(DataProvideReq dataProvideReq);

    void approve(long dataCollectionId);

    void reject(DataRejectReq dataRejectReq);

    List<DataCollection> getHistory();

    List<DataCollection> getApproved();

    List<DataCollection> getPending();
}
