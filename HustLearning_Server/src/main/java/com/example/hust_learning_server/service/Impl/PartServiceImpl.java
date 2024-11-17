package com.example.hust_learning_server.service.Impl;

import com.example.hust_learning_server.dto.request.PartReq;
import com.example.hust_learning_server.dto.response.PartRes;
import com.example.hust_learning_server.entity.Lesson;
import com.example.hust_learning_server.entity.Part;
import com.example.hust_learning_server.entity.Vocabulary;
import com.example.hust_learning_server.exception.ResourceNotFoundException;
import com.example.hust_learning_server.repository.LessonRepository;
import com.example.hust_learning_server.repository.PartRepository;
import com.example.hust_learning_server.repository.VocabularyRepository;
import com.example.hust_learning_server.service.PartService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PartServiceImpl implements PartService {
    private final PartRepository partRepository;
    private final LessonRepository lessonRepository;
    private final VocabularyRepository vocabularyRepository;

    @Override
    public void add(PartReq partReq) {
        Lesson lesson = lessonRepository.findById(partReq.getLessonId()).orElseThrow(ResourceNotFoundException::new);

        Part part = Part.builder()
                .partName(partReq.getPartName())
                .imageLocation(partReq.getImageLocation())
                .videoLocation(partReq.getVideoLocation())
                .lessonId(lesson.getId())
                .build();
        partRepository.save(part);
    }

    @Override
    public PartRes getById(Long partId) {
        Part part = partRepository.findById(partId).orElseThrow(ResourceNotFoundException::new);
        Lesson lesson = lessonRepository.findById(part.getLessonId()).orElse(null);
        return PartRes.builder()
                .partId(part.getId())
                .partName(part.getPartName())
                .imageLocation(part.getImageLocation())
                .videoLocation(part.getVideoLocation())
                .lessonId(part.getLessonId())
                .build();
    }

    @Override
    public List<PartRes> getAll(Long lessonId) {
        List<Part> parts;
        List<PartRes> partResList = new ArrayList<>();
        if (lessonId == null) {
            parts = partRepository.findAll();
            partResList = new ArrayList<>();
            for (Part part : parts) {
                PartRes partRes = PartRes.builder()
                        .partId(part.getId())
                        .partName(part.getPartName())
                        .imageLocation(part.getImageLocation())
                        .videoLocation(part.getVideoLocation())
                        .lessonId(part.getLessonId())
                        .build();
                partResList.add(partRes);
            }
            return partResList;
        } else {
            Lesson lesson = lessonRepository.findById(lessonId).orElseThrow(ResourceNotFoundException::new);
            parts = partRepository.findAllByLessonId(lessonId);
            for (Part part : parts) {
                PartRes partRes = PartRes.builder()
                        .partId(part.getId())
                        .partName(part.getPartName())
                        .imageLocation(part.getImageLocation())
                        .videoLocation(part.getVideoLocation())
                        .lessonId(part.getLessonId())
                        .build();
                partRes.setLessonId(lesson.getId());
                partResList.add(partRes);
            }
            return partResList;
        }
    }

    @Override
    public void update(PartRes partRes) {
        Part part = partRepository.findById(partRes.getPartId()).orElseThrow(ResourceNotFoundException::new);

        if (partRes.getPartName() != null) {
            part.setPartName(partRes.getPartName().trim());
        }
        if (partRes.getImageLocation() != null) {
            part.setImageLocation(partRes.getImageLocation().trim());
        }
        if (partRes.getVideoLocation() != null) {
            part.setVideoLocation(partRes.getVideoLocation().trim());
        }
        partRepository.save(part);
    }

    @Override
    public void deleteById(Long partId) {
        List<Vocabulary> vocabularies = vocabularyRepository.findAllByPartId(partId);
        vocabularies.forEach(vocabulary -> vocabulary.setPartId(null));
        vocabularyRepository.saveAll(vocabularies);
        partRepository.deleteById(partId);
    }
}