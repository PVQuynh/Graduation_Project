package com.example.hust_learning_server.service.Impl;

import com.example.hust_learning_server.dto.request.MobileReq;
import com.example.hust_learning_server.dto.response.MobileRes;
import com.example.hust_learning_server.entity.Mobile;
import com.example.hust_learning_server.exception.ResourceNotFoundException;
import com.example.hust_learning_server.repository.MobileRepository;
import com.example.hust_learning_server.service.MobileService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MobileServiceImpl implements MobileService {
    private final MobileRepository mobileRepository;

    @Override
    public void addLinkMobile(MobileReq mobileReq) {
        Mobile mobile = Mobile.builder()
                .mobileLocation(mobileReq.getMobileLocation())
                .build();
        mobileRepository.save(mobile);
    }

    @Override
    public void updateLinkMobile(long mobileId, MobileReq mobileReq) {
        Mobile mobile = mobileRepository.findById(mobileId).orElseThrow(ResourceNotFoundException::new);
        if (mobileReq.getMobileLocation() != null) {
            mobile.setMobileLocation(mobileReq.getMobileLocation());
        }
        mobileRepository.save(mobile);
    }

    @Override
    public List<MobileRes> getAllLinkMobile() {
        List<Mobile> mobileList = mobileRepository.findAll();
        List<MobileRes> mobileResList = new ArrayList<>();
        mobileList.forEach(mobile -> {
                    MobileRes mobileRes = MobileRes.builder()
                            .mobileId(mobile.getId())
                            .mobileLocation(mobile.getMobileLocation())
                            .build();
                    mobileResList.add(mobileRes);
                }
        );
        return mobileResList;
    }

    @Override
    public MobileRes getLinkMobile(long mobileId) {
        Optional<Mobile> mobileOptional = mobileRepository.findById(mobileId);
        MobileRes mobileRes = null;
        if (mobileOptional.isPresent()) {
            mobileRes = MobileRes.builder()
                    .mobileId(mobileOptional.get().getId())
                    .mobileLocation(mobileOptional.get().getMobileLocation())
                    .build();
        }
        return mobileRes;
    }

    @Override
    public void deleteLinkMobile(long mobileId) {
        mobileRepository.deleteById(mobileId);
    }
}
