package com.example.hust_learning_server.service.Impl;

import com.example.hust_learning_server.dto.request.PartVideoReq;
import com.example.hust_learning_server.dto.request.UpdatePartVideoReq;
import com.example.hust_learning_server.entity.Part;
import com.example.hust_learning_server.entity.PartVideo;
import com.example.hust_learning_server.exception.ConflictException;
import com.example.hust_learning_server.exception.ResourceNotFoundException;
import com.example.hust_learning_server.repository.PartRepository;
import com.example.hust_learning_server.repository.PartVideoRepository;
import com.example.hust_learning_server.service.PartVideoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.lang.module.ResolutionException;

@Service
@RequiredArgsConstructor
public class PartVideoServiceImpl implements PartVideoService {
    private final PartVideoRepository partVideoRepository;
    private final PartRepository partRepository;

    @Override
    public void updatePartVideo(UpdatePartVideoReq updatePartVideoReq) {
        PartVideo partVideo = partVideoRepository.findById(updatePartVideoReq.getPartVideoId()).orElseThrow(ResolutionException::new);
        partVideo.setVideoLocation(updatePartVideoReq.getVideoLocation());
        partVideoRepository.save(partVideo);
    }

    @Override
    public void addPartVideo(PartVideoReq partVideoReq) {
        Part part = partRepository.findById(partVideoReq.getPartId()).orElseThrow(ResourceNotFoundException::new);

        if (partVideoRepository.existsByVideoLocation(partVideoReq.getVideoLocation())) {
            throw new ConflictException();
        }

        PartVideo partVideo = PartVideo.builder()
                .videoLocation(partVideoReq.getVideoLocation())
                .partId(partVideoReq.getPartId())
                .build();

        partVideoRepository.save(partVideo);
    }

    @Override
    public void deleteById(long id) {
        partVideoRepository.deleteById(id);
    }
}
