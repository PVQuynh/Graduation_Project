package com.example.hust_learning_server.service.Impl;

import com.example.hust_learning_server.dto.request.UpdatePartImageReq;
import com.example.hust_learning_server.entity.PartImage;
import com.example.hust_learning_server.exception.ResourceNotFoundException;
import com.example.hust_learning_server.repository.PartImageRepository;
import com.example.hust_learning_server.service.PartImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PartImageServiceImpl implements PartImageService {
    private final PartImageRepository partImageRepository;

    @Override
    public void updatePartImage(UpdatePartImageReq updatePartImageReq) {
        PartImage partImage = partImageRepository.findById(updatePartImageReq.getPartImageId()).orElseThrow(ResourceNotFoundException::new);
        partImage.setImageLocation(updatePartImageReq.getImageLocation());
        partImageRepository.save(partImage);
    }
}
