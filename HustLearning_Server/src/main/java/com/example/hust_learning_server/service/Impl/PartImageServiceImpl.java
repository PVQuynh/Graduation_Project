package com.example.hust_learning_server.service.Impl;

import com.example.hust_learning_server.dto.request.PartImageReq;
import com.example.hust_learning_server.dto.request.UpdatePartImageReq;
import com.example.hust_learning_server.entity.Part;
import com.example.hust_learning_server.entity.PartImage;
import com.example.hust_learning_server.exception.ConflictException;
import com.example.hust_learning_server.exception.ResourceNotFoundException;
import com.example.hust_learning_server.repository.PartImageRepository;
import com.example.hust_learning_server.repository.PartRepository;
import com.example.hust_learning_server.service.PartImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PartImageServiceImpl implements PartImageService {
    private final PartImageRepository partImageRepository;
    private final PartRepository partRepository;

    @Override
    public void updatePartImage(UpdatePartImageReq updatePartImageReq) {
        PartImage partImage = partImageRepository.findById(updatePartImageReq.getPartImageId()).orElseThrow(ResourceNotFoundException::new);
        partImage.setImageLocation(updatePartImageReq.getImageLocation());
        partImageRepository.save(partImage);
    }

    @Override
    public void addPartImage(PartImageReq partImageReq) {
        Part part = partRepository.findById(partImageReq.getPartId()).orElseThrow(ResourceNotFoundException::new);

        if (partImageRepository.existsByImageLocationAndPartId(partImageReq.getImageLocation(), partImageReq.getPartId())) {
            throw new ConflictException();
        }

        PartImage partImage = PartImage.builder()
                .imageLocation(partImageReq.getImageLocation())
                .partId(partImageReq.getPartId())
                .build();

        partImageRepository.save(partImage);
    }

    @Override
    public void deleteById(long id) {
        partImageRepository.deleteById(id);
    }
}
