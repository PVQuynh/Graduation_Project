package com.example.DataCollection.service.Impl;

import com.example.DataCollection.constant.DataStatus;
import com.example.DataCollection.dto.request.DataProvideReq;
import com.example.DataCollection.dto.request.DataRejectReq;
import com.example.DataCollection.entity.DataCollection;
import com.example.DataCollection.enum_constant.DataType;
import com.example.DataCollection.exception.BusinessLogicException;
import com.example.DataCollection.repository.DataCollectionRepository;
import com.example.DataCollection.service.DataCollectionService;
import com.example.DataCollection.utils.EmailUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DataCollectionServiceImpl implements DataCollectionService {
    private final DataCollectionRepository dataCollectionRepository;

    @Override
    public void sendData(DataProvideReq dataProvideReq) {
        String email = EmailUtils.getCurrentUser();
        if (!ObjectUtils.isEmpty(email)) {
            DataCollection dataCollection = DataCollection.builder().dataLocation(dataProvideReq.getDataLocation())
                    .volunteerEmail(email)
                    .content(dataProvideReq.getContent()).dataType(DataType.valueOf(dataProvideReq.getDataType()))
                    .status(DataStatus.WAITING)
                    .build();
            dataCollectionRepository.save(dataCollection);
        }

    }

    @Override
    public void approve(long dataCollectionId) {
        String email = EmailUtils.getCurrentUser();
        if (!ObjectUtils.isEmpty(email)) {
            DataCollection dataCollection = dataCollectionRepository.findById(dataCollectionId)
                    .orElseThrow(() -> new BusinessLogicException());
            if (dataCollection.getStatus()== DataStatus.APPROVED) {
                throw new BusinessLogicException();
            }
            dataCollection.setStatus(DataStatus.APPROVED);
            dataCollection.setAdminEmail(email);
            dataCollectionRepository.save(dataCollection);
        }
    }

    @Override
    public void reject(DataRejectReq dataRejectReq) {
        String email = EmailUtils.getCurrentUser();
        if (!ObjectUtils.isEmpty(email)) {
            DataCollection dataCollection = dataCollectionRepository.findById(dataRejectReq.dataCollectionId)
                    .orElseThrow(() -> new BusinessLogicException());
            if (dataCollection.getStatus() == DataStatus.REJECTED) {
                throw new BusinessLogicException();
            }
            dataCollection.setStatus(DataStatus.REJECTED);
            dataCollection.setAdminEmail(email);
            dataCollection.setFeedBack(dataRejectReq.feedBack);
            dataCollectionRepository.save(dataCollection);
        }
    }

    @Override
    public List<DataCollection> getHistory() {
        String email = EmailUtils.getCurrentUser();
        if (!ObjectUtils.isEmpty(email))  {
            return dataCollectionRepository.findByEmail(email).orElse(null);

        };
        return null;
    }

    @Override
    public List<DataCollection> getApproved() {
        String email = EmailUtils.getCurrentUser();
        if (!ObjectUtils.isEmpty(email))  {
            return dataCollectionRepository.getApproved(email).orElse(null);

        };
        return null;
    }

    @Override
    public List<DataCollection> getPending() {

            return dataCollectionRepository.getPending().orElse(null);


    }
}
