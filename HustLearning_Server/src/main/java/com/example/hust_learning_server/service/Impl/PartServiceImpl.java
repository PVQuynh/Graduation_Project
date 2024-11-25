package com.example.hust_learning_server.service.Impl;

import com.example.hust_learning_server.dto.request.PartReq;
import com.example.hust_learning_server.dto.request.UpdatePartReq;
import com.example.hust_learning_server.dto.response.PartImageRes;
import com.example.hust_learning_server.dto.response.PartRes;
import com.example.hust_learning_server.dto.response.PartVideoRes;
import com.example.hust_learning_server.entity.Part;
import com.example.hust_learning_server.entity.PartImage;
import com.example.hust_learning_server.entity.PartVideo;
import com.example.hust_learning_server.exception.ConflictException;
import com.example.hust_learning_server.exception.ResourceNotFoundException;
import com.example.hust_learning_server.repository.PartImageRepository;
import com.example.hust_learning_server.repository.PartRepository;
import com.example.hust_learning_server.repository.PartVideoRepository;
import com.example.hust_learning_server.service.PartService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@Service
@RequiredArgsConstructor
public class PartServiceImpl implements PartService {
    private final PartRepository partRepository;
    private final PartImageRepository partImageRepository;
    private final PartVideoRepository partVideoRepository;

    @Override
    public void addPart(PartReq partReq) {
        if (partRepository.existsByPartNameAndLessonId(partReq.getPartName(), partReq.getLessonId())) throw new ConflictException();

        Part p = Part.builder()
                .partName(partReq.getPartName())
                .lessonId(partReq.getLessonId())
                .build();
        Part part = partRepository.save(p);

        CompletableFuture.runAsync(() -> {
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
        });
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
            parts.forEach(part -> {
                PartRes partRes = PartRes.builder()
                        .partId(part.getId())
                        .partName(part.getPartName())
                        .lessonId(part.getLessonId())
                        .build();

                List<PartImageRes> partImageResList = Collections.synchronizedList(new ArrayList<>());
                List<PartVideoRes> partVideoResList = Collections.synchronizedList(new ArrayList<>());

                CompletableFuture<?> partImageFuture = CompletableFuture.runAsync(() -> {
                    List<PartImage> partImages = partImageRepository.findByPartId(part.getId());
                    partImages.parallelStream().forEach(partImage -> {
                        PartImageRes partImageRes = PartImageRes.builder()
                                .partImageId(partImage.getId())
                                .imageLocation(partImage.getImageLocation())
                                .partId(partImage.getPartId())
                                .build();
                        partImageResList.add(partImageRes);
                    });
                });

                CompletableFuture<?> partVideoFuture = CompletableFuture.runAsync(() -> {
                    List<PartVideo> partVideos = partVideoRepository.findByPartId(part.getId());
                    partVideos.parallelStream().forEach(partVideo -> {
                        PartVideoRes partVideoRes = PartVideoRes.builder()
                                .partVideoId(partVideo.getId())
                                .videoLocation(partVideo.getVideoLocation())
                                .partId(partVideo.getPartId())
                                .build();
                        partVideoResList.add(partVideoRes);
                    });
                });

                partImageFuture.join();
                partVideoFuture.join();

                partRes.setPartImageResList(partImageResList);
                partRes.setPartVideoResList(partVideoResList);
                partResList.add(partRes);
            });
        } else {
            List<Part> parts = partRepository.findAllByLessonId(lessonId);
            parts.forEach(part -> {
                PartRes partRes = PartRes.builder()
                        .partId(part.getId())
                        .partName(part.getPartName())
                        .lessonId(part.getLessonId())
                        .build();

                List<PartImageRes> partImageResList = Collections.synchronizedList(new ArrayList<>());
                List<PartVideoRes> partVideoResList = Collections.synchronizedList(new ArrayList<>());

                CompletableFuture<?> partImageFuture = CompletableFuture.runAsync(() -> {
                    List<PartImage> partImages = partImageRepository.findByPartId(part.getId());
                    partImages.parallelStream().forEach(partImage -> {
                        PartImageRes partImageRes = PartImageRes.builder()
                                .partImageId(partImage.getId())
                                .imageLocation(partImage.getImageLocation())
                                .partId(partImage.getPartId())
                                .build();
                        partImageResList.add(partImageRes);
                    });
                });

                CompletableFuture<?> partVideoFuture = CompletableFuture.runAsync(() -> {
                    List<PartVideo> partVideos = partVideoRepository.findByPartId(part.getId());
                    partVideos.parallelStream().forEach(partVideo -> {
                        PartVideoRes partVideoRes = PartVideoRes.builder()
                                .partVideoId(partVideo.getId())
                                .videoLocation(partVideo.getVideoLocation())
                                .partId(partVideo.getPartId())
                                .build();
                        partVideoResList.add(partVideoRes);
                    });
                });

                partImageFuture.join();
                partVideoFuture.join();

                partRes.setPartImageResList(partImageResList);
                partRes.setPartVideoResList(partVideoResList);
                partResList.add(partRes);
            });
        }
        return partResList;
    }

    @Override
    public void deletePart(long partId) {
        Part part = partRepository.findById(partId).orElseThrow(ResourceNotFoundException::new);
        partRepository.delete(part);
        CompletableFuture.runAsync(() -> {
            List<PartImage> partImages = partImageRepository.findByPartId(part.getId());
            List<PartVideo> partVideos = partVideoRepository.findByPartId(part.getId());
            partImageRepository.deleteAll(partImages);
            partVideoRepository.deleteAll(partVideos);
        });
    }

    @Override
    public void updatePart(UpdatePartReq updatePartReq) {
        Part part = partRepository.findById(updatePartReq.getPartId()).orElseThrow(ResourceNotFoundException::new);
        part.setPartName(updatePartReq.getPartName());
        partRepository.save(part);
    }
}
