package com.example.hust_learning_server.service.Impl;

import com.example.hust_learning_server.dto.request.SetPrimaryForVocabularyVideo;
import com.example.hust_learning_server.dto.request.UpdateVocabularyVideoReq;
import com.example.hust_learning_server.dto.request.VocabularyVideoReq;
import com.example.hust_learning_server.entity.VocabularyVideo;
import com.example.hust_learning_server.exception.BusinessLogicException;
import com.example.hust_learning_server.mapper.VocabularyVideoMapper;
import com.example.hust_learning_server.repository.VocabularyVideoRepository;
import com.example.hust_learning_server.service.VocabularyVideoService;
import com.example.hust_learning_server.utils.EmailUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.List;

@Service
@RequiredArgsConstructor
public class VocabularyVideoServiceImpl implements VocabularyVideoService {

    private final VocabularyVideoRepository vocabularyVideoRepository;

    private final VocabularyVideoMapper vocabularyVideoMapper;


    @Override
    public void addVocabularyVideo(VocabularyVideoReq vocabularyVideoReq) {
        String email = EmailUtils.getCurrentUser();
        if (ObjectUtils.isEmpty(email)) {
            throw new BusinessLogicException();
        }
        VocabularyVideo vocabularyVideo = vocabularyVideoMapper.toEntity(vocabularyVideoReq);

        if (vocabularyVideo.isPrimary()) {
            List<VocabularyVideo> vocabularyVideoList = vocabularyVideoRepository.findAllByVocabularyId(vocabularyVideoReq.getVocabularyId());
            for (VocabularyVideo video : vocabularyVideoList) {
                video.setPrimary(false);
            }
            vocabularyVideoList.add(vocabularyVideo);
            vocabularyVideoRepository.saveAll(vocabularyVideoList);
        } else {
            vocabularyVideoRepository.save(vocabularyVideo);
        }
    }

    @Override
    public void addVocabularyVideoList(List<VocabularyVideoReq> vocabularyVideoReqList) {
        String email = EmailUtils.getCurrentUser();
        if (ObjectUtils.isEmpty(email)) {
            throw new BusinessLogicException();
        }

        List<VocabularyVideo> vocabularyVideoList = vocabularyVideoMapper.toEntityList(vocabularyVideoReqList);

        // neu la primary thi chuyen toan bo con lai ve false
        for (VocabularyVideo video : vocabularyVideoList) {
            if (video.isPrimary()) {
                List<VocabularyVideo> vocabularyVideoListByVocabId = vocabularyVideoRepository.findAllByVocabularyId(video.getVocabulary().getId());
                for (VocabularyVideo video1 : vocabularyVideoListByVocabId) {
                    video1.setPrimary(false);
                }
                vocabularyVideoListByVocabId.add(video);
                vocabularyVideoRepository.saveAll(vocabularyVideoListByVocabId);
            }
        }

        vocabularyVideoRepository.saveAll(vocabularyVideoList);
    }

    @Override
    public void updateVocabularyVideo(UpdateVocabularyVideoReq updateVocabularyVideoReq) {
        String email = EmailUtils.getCurrentUser();
        if (ObjectUtils.isEmpty(email)) {
            throw new BusinessLogicException();
        }

        // lay ra tu db de update
        VocabularyVideo vocabularyVideo = vocabularyVideoRepository.findById(updateVocabularyVideoReq.getVocabularyVideoId()).orElseThrow(BusinessLogicException::new);

        // neu update là true thi set toan bo con lai la false
        if (updateVocabularyVideoReq.isPrimary()) {
            List<VocabularyVideo> vocabularyVideoList = vocabularyVideoRepository.findAllByVocabularyId(vocabularyVideo.getVocabulary().getId());
            for (VocabularyVideo video : vocabularyVideoList) {
                video.setPrimary(false);
            }

            // set primary cho video va update
            vocabularyVideo.setPrimary(true);
            vocabularyVideo.setVideoLocation(updateVocabularyVideoReq.getVideoLocation());
            vocabularyVideoRepository.saveAll(vocabularyVideoList);
        } else {
            if (updateVocabularyVideoReq.getVideoLocation() != null)
                vocabularyVideo.setVideoLocation(updateVocabularyVideoReq.getVideoLocation());
            vocabularyVideo.setPrimary(false);
            vocabularyVideoRepository.save(vocabularyVideo);
        }
    }
    @Override
    public void setPrimaryForVocabularyVideo(SetPrimaryForVocabularyVideo setPrimaryForVocabularyVideo) {
        String email = EmailUtils.getCurrentUser();
        if (ObjectUtils.isEmpty(email)) {
            throw new BusinessLogicException();
        }

        VocabularyVideo vocabularyVideo = vocabularyVideoRepository.findById(setPrimaryForVocabularyVideo.getVocabularyVideoId()).orElseThrow(BusinessLogicException::new);

        if (setPrimaryForVocabularyVideo.isPrimary()) {
            List<VocabularyVideo> vocabularyVideoList = vocabularyVideoRepository.findAllByVocabularyId(vocabularyVideo.getVocabulary().getId());
            for (VocabularyVideo video : vocabularyVideoList) {
                video.setPrimary(false);
            }
            vocabularyVideoList.add(vocabularyVideo);
            vocabularyVideoRepository.saveAll(vocabularyVideoList);

            // luu
            vocabularyVideo.setPrimary(true);
            vocabularyVideoRepository.save(vocabularyVideo);
        }
    }

    @Override
    public void deleteById(long id) {
        String email = EmailUtils.getCurrentUser();
        if (ObjectUtils.isEmpty(email)) {
            throw new BusinessLogicException();
        }

        vocabularyVideoRepository.deleteById(id);
    }
}
