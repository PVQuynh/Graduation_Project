package com.example.hust_learning_server.mapper.Impl;

import com.example.hust_learning_server.dto.request.VocabularyImageReq;
import com.example.hust_learning_server.dto.request.VocabularyReq;
import com.example.hust_learning_server.dto.request.VocabularyVideoReq;
import com.example.hust_learning_server.dto.response.VocabularyImageRes;
import com.example.hust_learning_server.dto.response.VocabularyRes;
import com.example.hust_learning_server.dto.response.VocabularyVideoRes;
import com.example.hust_learning_server.entity.*;
import com.example.hust_learning_server.mapper.VocabularyMapper;
import com.example.hust_learning_server.mapper.VocabularyImageMapper;
import com.example.hust_learning_server.mapper.VocabularyVideoMapper;
import com.example.hust_learning_server.repository.LessonRepository;
import com.example.hust_learning_server.repository.TopicRepository;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class VocabularyMapperImpl implements VocabularyMapper {
    private final LessonRepository lessonRepository;
    private final TopicRepository topicRepository;
    private final VocabularyImageMapper vocabularyImageMapper;
    private final VocabularyVideoMapper vocabularyVideoMapper;

    @Override
    public Vocabulary toEntity(VocabularyReq dto) {
        Vocabulary vocabulary = Vocabulary.builder()
                .content(dto.getContent())
                .note(dto.getNote())
                .vocabularyType(dto.getVocabularyType())
                .partId(dto.getPartId())
                .build();
        vocabulary.setId(0);

        // set image
        List<VocabularyImageReq> vocabularyImageListReqs = dto.getVocabularyImageReqs();
        if (ObjectUtils.isNotEmpty(vocabularyImageListReqs)) {
            List<VocabularyImage> vocabularyImageList = vocabularyImageMapper.toEntityList(vocabularyImageListReqs);
            vocabularyImageList.forEach(vocabularyImage -> vocabularyImage.setVocabulary(vocabulary));
            vocabulary.setVocabularyImages(vocabularyImageList);
        }

        // set video
        List<VocabularyVideoReq> vocabularyVideoListReqs = dto.getVocabularyVideoReqs();
        if (ObjectUtils.isNotEmpty(vocabularyVideoListReqs)) {
            List<VocabularyVideo> vocabularyVideoList = vocabularyVideoMapper.toEntityList(vocabularyVideoListReqs);
            vocabularyVideoList.forEach(vocabularyVideo -> vocabularyVideo.setVocabulary(vocabulary));
            vocabulary.setVocabularyVideos(vocabularyVideoList);
        }

        // set topic
        Topic topic = topicRepository.findById(dto.getTopicId()).orElse(null);
        vocabulary.setTopic(topic);

        // set lessonId
        Lesson lesson = lessonRepository.findById(dto.getLessonId()).orElse(null);
        if (lesson != null) {
            vocabulary.setLessonId(lesson.getId());
        } else {
            vocabulary.setLessonId(null);
        }
        return vocabulary;
    }

    @Override
    public VocabularyRes toDTO(Vocabulary entity) {
        VocabularyRes vocabularyRes = VocabularyRes.builder()
                .vocabularyId(entity.getId())
                .content(entity.getContent())
                .note(entity.getNote())
                .vocabularyType(entity.getVocabularyType())
                .partId(entity.getPartId() == null ? 0 : entity.getPartId())
                .build();

        // set image
        List<VocabularyImage> vocabularyImageList = entity.getVocabularyImages();
        List<VocabularyImageRes> vocabularyImageResList = vocabularyImageMapper.toDTOList(vocabularyImageList);
        vocabularyRes.setVocabularyImageResList(vocabularyImageResList);

        // set video
        List<VocabularyVideo> vocabularyVideoList = entity.getVocabularyVideos();
        List<VocabularyVideoRes> vocabularyVideoResList = vocabularyVideoMapper.toDTOList(vocabularyVideoList);
        vocabularyRes.setVocabularyVideoResList(vocabularyVideoResList);

        // set topic
        vocabularyRes.setTopicId(Objects.nonNull(entity.getTopic()) ? entity.getTopic().getId() : 0);
        vocabularyRes.setTopicContent(Objects.nonNull(entity.getTopic()) ? entity.getTopic().getContent() : null);

        // set lesson
        if (entity.getLessonId() != null) {
            Lesson part = lessonRepository.findById(entity.getLessonId()).orElse(null);
            if (part != null) {
                vocabularyRes.setLessonId(part.getId());
                vocabularyRes.setLessonName(part.getLessonName());
            }
        } else {
            vocabularyRes.setLessonId(0);
            vocabularyRes.setLessonName(null);
        }
        return vocabularyRes;
    }

    @Override
    public List<VocabularyRes> toDTOList(List<Vocabulary> entityList) {
        return entityList.stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<Vocabulary> toEntityList(List<VocabularyReq> dtoList) {
        return dtoList.stream()
                .map(this::toEntity)
                .collect(Collectors.toList());
    }

}
