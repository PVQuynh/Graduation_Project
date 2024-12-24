package com.example.hust_learning_server.service.Impl;

import com.example.hust_learning_server.dto.request.PartReq;
import com.example.hust_learning_server.dto.request.UpdatePartReq;
import com.example.hust_learning_server.dto.response.PartImageRes;
import com.example.hust_learning_server.dto.response.PartRes;
import com.example.hust_learning_server.dto.response.PartVideoRes;
import com.example.hust_learning_server.entity.*;
import com.example.hust_learning_server.exception.ResourceNotFoundException;
import com.example.hust_learning_server.repository.*;
import com.example.hust_learning_server.service.PartService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

@Service
@RequiredArgsConstructor
public class PartServiceImpl implements PartService {
    private final PartRepository partRepository;
    private final PartImageRepository partImageRepository;
    private final PartVideoRepository partVideoRepository;
    private final LessonRepository lessonRepository;
    private final ClassRoomRepository classRoomRepository;

    @Override
    public void addPart(PartReq partReq) {
        if (partRepository.existsByPartNameAndLessonId(partReq.getPartName(), partReq.getLessonId()))
            return;

        Part p = Part.builder()
                .partName(partReq.getPartName())
                .lessonId(partReq.getLessonId())
                .build();
        Part part = partRepository.save(p);

        CompletableFuture.runAsync(() -> {
            List<PartImage> partImages = new ArrayList<>();
            List<PartVideo> partVideos = new ArrayList<>();
            if (partReq.getPartImageReqs() != null) {
                partReq.getPartImageReqs().forEach(partImageReq -> {
                    PartImage partImage = PartImage.builder()
                            .imageLocation(partImageReq.getImageLocation())
                            .partId(part.getId())
                            .build();
                    partImages.add(partImage);
                    partImageRepository.saveAll(partImages);
                });
            }
            if (partReq.getPartVideoReqs() != null) {
                partReq.getPartVideoReqs().forEach(partVideoReq -> {
                    PartVideo partVideo = PartVideo.builder()
                            .videoLocation(partVideoReq.getVideoLocation())
                            .partId(part.getId())
                            .build();
                    partVideos.add(partVideo);
                });
                partVideoRepository.saveAll(partVideos);
            }
        });
    }

    @Override
    public void addParts(List<PartReq> partReqList) {
        partReqList.forEach(this::addPart);
    }

    @Override
    public PartRes getPart(long partId) {
        Part part = partRepository.findById(partId).orElseThrow(ResourceNotFoundException::new);

        PartRes partRes = new PartRes();
        partRes.setPartId(part.getId());
        partRes.setPartName(part.getPartName());
        lessonRepository.findById(part.getLessonId()).ifPresent(lesson -> {
            partRes.setLessonId(lesson.getId());
            partRes.setLessonName(lesson.getLessonName());
            classRoomRepository.findById(lesson.getClassRoomId()).ifPresent(classRoom -> {
                partRes.setClassRoomId(classRoom.getId());
                partRes.setClassRoomName(classRoom.getContent());
            });
        });

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
        return partRes;
    }

    @Override
    public List<PartRes> getAllParts(long classRoomId, long lessonId, String searchContent) {
        List<PartRes> partResList = new ArrayList<>();
        List<Part> parts = partRepository.findAllParts(classRoomId, lessonId, searchContent);
        parts.forEach(part -> {
            PartRes partRes = new PartRes();
            partRes.setPartId(part.getId());
            partRes.setPartName(part.getPartName());
            lessonRepository.findById(part.getLessonId()).ifPresent(lesson -> {
                partRes.setLessonId(lesson.getId());
                partRes.setLessonName(lesson.getLessonName());
                classRoomRepository.findById(lesson.getClassRoomId()).ifPresent(classRoom -> {
                    partRes.setClassRoomId(classRoom.getId());
                    partRes.setClassRoomName(classRoom.getContent());
                });
            });

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
