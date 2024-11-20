package com.example.hust_learning_server.service.Impl;

import com.example.hust_learning_server.dto.request.UpdatePartVideoReq;
import com.example.hust_learning_server.entity.PartVideo;
import com.example.hust_learning_server.repository.PartVideoRepository;
import com.example.hust_learning_server.service.PartVideoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.lang.module.ResolutionException;

@Service
@RequiredArgsConstructor
public class PartVideoServiceImpl implements PartVideoService {
    private final PartVideoRepository partVideoRepository;

    @Override
    public void updatePartVideo(UpdatePartVideoReq updatePartVideoReq) {
        PartVideo partVideo = partVideoRepository.findById(updatePartVideoReq.getPartVideoId()).orElseThrow(ResolutionException::new);
        partVideo.setVideoLocation(updatePartVideoReq.getVideoLocation());
        partVideoRepository.save(partVideo);
    }
}
