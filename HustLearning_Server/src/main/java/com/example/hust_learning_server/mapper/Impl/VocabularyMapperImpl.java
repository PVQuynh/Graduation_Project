package com.example.hust_learning_server.mapper.Impl;

import com.example.hust_learning_server.dto.request.VocabularyImageReq;
import com.example.hust_learning_server.dto.request.VocabularyReq;
import com.example.hust_learning_server.dto.request.VocabularyVideoReq;
import com.example.hust_learning_server.dto.response.VocabularyImageRes;
import com.example.hust_learning_server.dto.response.VocabularyRes;
import com.example.hust_learning_server.dto.response.VocabularyVideoRes;
import com.example.hust_learning_server.entity.Topic;
import com.example.hust_learning_server.entity.Vocabulary;
import com.example.hust_learning_server.entity.VocabularyImage;
import com.example.hust_learning_server.entity.VocabularyVideo;
import com.example.hust_learning_server.mapper.VocabularyMapper;
import com.example.hust_learning_server.mapper.VocabularyImageMapper;
import com.example.hust_learning_server.mapper.VocabularyVideoMapper;
import com.example.hust_learning_server.repository.TopicRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;
@Component
@RequiredArgsConstructor
public class VocabularyMapperImpl implements VocabularyMapper {

    private final TopicRepository topicRepository;
    private final VocabularyImageMapper vocabularyImageMapper;
    private final VocabularyVideoMapper vocabularyVideoMapper;

    @Override
    public Vocabulary toEntity(VocabularyReq dto) {
        ModelMapper modelMapper = new ModelMapper();
        Vocabulary vocabulary = modelMapper.map(dto, Vocabulary.class);
        vocabulary.setId(0);

        // set image
        List<VocabularyImageReq> vocabularyImageListReqs = dto.getVocabularyImageReqs();
        List<VocabularyImage> vocabularyImageList = vocabularyImageMapper.toEntityList(vocabularyImageListReqs);
        vocabularyImageList.forEach(vocabularyImage -> vocabularyImage.setVocabulary(vocabulary));
        vocabulary.setVocabularyImages(vocabularyImageList);
        
        // set video
        List<VocabularyVideoReq> vocabularyVideoListReqs = dto.getVocabularyVideoReqs();
        List<VocabularyVideo> vocabularyVideoList = vocabularyVideoMapper.toEntityList(vocabularyVideoListReqs);
        vocabularyVideoList.forEach(vocabularyVideo -> vocabularyVideo.setVocabulary(vocabulary));
        vocabulary.setVocabularyVideos(vocabularyVideoList);

        // set topic
        Topic topic = topicRepository.findById(dto.getTopicId()).orElse(null);
        vocabulary.setTopic(topic);

        return vocabulary;
    }

    @Override
    public VocabularyRes toDTO(Vocabulary entity) {
        ModelMapper modelMapper = new ModelMapper();
        VocabularyRes vocabularyRes = modelMapper.map(entity, VocabularyRes.class);

        // set image
        List<VocabularyImage> vocabularyImageList = entity.getVocabularyImages();
        List<VocabularyImageRes> vocabularyImageResList = vocabularyImageMapper.toDTOList(vocabularyImageList);
        vocabularyRes.setVocabularyImageResList(vocabularyImageResList);

        // set video
        List<VocabularyVideo> vocabularyVideoList = entity.getVocabularyVideos();
        List<VocabularyVideoRes> vocabularyVideoResList = vocabularyVideoMapper.toDTOList(vocabularyVideoList);
        vocabularyRes.setVocabularyVideoResList(vocabularyVideoResList);

        // set topic
        vocabularyRes.setTopicId(entity.getTopic().getId());
        vocabularyRes.setTopicContent(entity.getTopic().getContent());

        return vocabularyRes;
    }

    @Override
    public List<VocabularyRes> toDTOList(List<Vocabulary> entityList) {
        return entityList.stream()
                .map(entity->toDTO(entity))
                .collect(Collectors.toList());
    }

    @Override
    public List<Vocabulary> toEntityList(List<VocabularyReq> dtoList) {
        return dtoList.stream()
                .map(dto->toEntity(dto))
                .collect(Collectors.toList());
    }

}
