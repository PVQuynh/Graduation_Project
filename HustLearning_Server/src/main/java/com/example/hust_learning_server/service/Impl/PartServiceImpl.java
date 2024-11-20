package com.example.hust_learning_server.service.Impl;

import com.example.hust_learning_server.dto.request.PartReq;
import com.example.hust_learning_server.dto.response.PartImageRes;
import com.example.hust_learning_server.dto.response.PartRes;
import com.example.hust_learning_server.dto.response.PartVideoRes;
import com.example.hust_learning_server.entity.Part;
import com.example.hust_learning_server.entity.PartImage;
import com.example.hust_learning_server.entity.PartVideo;
import com.example.hust_learning_server.repository.PartImageRepository;
import com.example.hust_learning_server.repository.PartRepository;
import com.example.hust_learning_server.repository.PartVideoRepository;
import com.example.hust_learning_server.service.PartService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PartServiceImpl implements PartService {
    private final PartRepository partRepository;
    private final PartImageRepository partImageRepository;
    private final PartVideoRepository partVideoRepository;

    @Override
    public void addPart(PartReq partReq) {
        Part p = Part.builder()
                .partName(partReq.getPartName())
                .lessonId(partReq.getLessonId())
                .build();
        Part part = partRepository.save(p);

        List<PartImage> partImages = new ArrayList<>();
        List<PartVideo> partVideos = new ArrayList<>();
        partReq.getPartImageReqs().forEach(partImageReq -> {
            PartImage partImage = PartImage.builder()
                    .imageLocation(partImageReq.getImageLocation())
                    .partId(part.getId())
                    .build();
            partImages.add(partImage);
        });
        partReq.getPartVideoReqs().forEach(partVideoReq -> {
            PartVideo partVideo = PartVideo.builder()
                    .videoLocation(partVideoReq.getVideoLocation())
                    .partId(part.getId())
                    .build();
            partVideos.add(partVideo);
        });
        partImageRepository.saveAll(partImages);
        partVideoRepository.saveAll(partVideos);
    }

    @Override
    public void addParts(List<PartReq> partReqList) {
        partReqList.forEach(this::addPart);
    }

    @Override
    public List<PartRes> getAllParts(long lessonId) {
        List<PartRes> partResList = new ArrayList<>();
        if (lessonId == 0) {
            List<Part> parts = partRepository.findAll();
            for (Part part : parts) {
                PartRes partRes = PartRes.builder()
                        .partId(part.getId())
                        .partName(part.getPartName())
                        .lessonId(part.getLessonId())
                        .build();
                
                List<PartImage> partImages = partImageRepository.findByPartId(part.getId());
                List<PartImageRes> partImageResList = new ArrayList<>();
                for (PartImage partImage : partImages) {
                    PartImageRes partImageRes = PartImageRes.builder()
                            .partImageId(partImage.getId())
                            .imageLocation(partImage.getImageLocation())
                            .partId(partImage.getPartId())
                            .build();
                    partImageResList.add(partImageRes);
                }
                List<PartVideo> partVideos = partVideoRepository.findByPartId(part.getId());
                List<PartVideoRes> partVideoResList = new ArrayList<>();
                for (PartVideo partVideo : partVideos) {
                    PartVideoRes partVideoRes = PartVideoRes.builder()
                            .partVideoId(partVideo.getId())
                            .videoLocation(partVideo.getVideoLocation())
                            .partId(partVideo.getPartId())
                            .build();
                    partVideoResList.add(partVideoRes);
                }
                
                partRes.setPartImageResList(partImageResList);
                partRes.setPartVideoResList(partVideoResList);
                partResList.add(partRes);
            }
        } {
            List<Part> parts = partRepository.findAllByLessonId(lessonId);
            for (Part part : parts) {
                PartRes partRes = PartRes.builder()
                        .partId(part.getId())
                        .partName(part.getPartName())
                        .lessonId(part.getLessonId())
                        .build();

                List<PartImage> partImages = partImageRepository.findByPartId(part.getId());
                List<PartImageRes> partImageResList = new ArrayList<>();
                for (PartImage partImage : partImages) {
                    PartImageRes partImageRes = PartImageRes.builder()
                            .partImageId(partImage.getId())
                            .imageLocation(partImage.getImageLocation())
                            .partId(partImage.getPartId())
                            .build();
                    partImageResList.add(partImageRes);
                }
                List<PartVideo> partVideos = partVideoRepository.findByPartId(part.getId());
                List<PartVideoRes> partVideoResList = new ArrayList<>();
                for (PartVideo partVideo : partVideos) {
                    PartVideoRes partVideoRes = PartVideoRes.builder()
                            .partVideoId(partVideo.getId())
                            .videoLocation(partVideo.getVideoLocation())
                            .partId(partVideo.getPartId())
                            .build();
                    partVideoResList.add(partVideoRes);
                }

                partRes.setPartImageResList(partImageResList);
                partRes.setPartVideoResList(partVideoResList);
                partResList.add(partRes);
            } 
        }
        return partResList;
    }
}
