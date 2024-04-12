package com.example.hust_learning_server.service.Impl;

import com.example.hust_learning_server.dto.request.SetPrimaryForVocabularyMedium;
import com.example.hust_learning_server.dto.request.UpdateVocabularyMediumReq;
import com.example.hust_learning_server.dto.request.VocabularyMediumReq;
import com.example.hust_learning_server.entity.Vocabulary;
import com.example.hust_learning_server.entity.VocabularyMedium;
import com.example.hust_learning_server.exception.BusinessLogicException;
import com.example.hust_learning_server.mapper.VocabularyMediumMapper;
import com.example.hust_learning_server.repository.VocabularyMediumRepository;
import com.example.hust_learning_server.service.VocabularyMediumService;
import com.example.hust_learning_server.utils.EmailUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class VocabularyMediumServiceImpl implements VocabularyMediumService {

    private final VocabularyMediumRepository vocabularyMediumRepository;

    private final VocabularyMediumMapper vocabularyMediumMapper;


    @Override
    public void addVocabularyMedium(VocabularyMediumReq vocabularyMediumReq) {
        String email = EmailUtils.getCurrentUser();
        if (ObjectUtils.isEmpty(email)) {
            throw new BusinessLogicException();
        }
        VocabularyMedium vocabularyMedium = vocabularyMediumMapper.toEntity(vocabularyMediumReq);

        if (vocabularyMedium.isPrimary()) {
            List<VocabularyMedium> vocabularyMediumList = vocabularyMediumRepository.findAllByVocabularyId(vocabularyMediumReq.getVocabularyId());
            for (VocabularyMedium medium : vocabularyMediumList) {
                medium.setPrimary(false);
            }
            vocabularyMediumList.add(vocabularyMedium);
            vocabularyMediumRepository.saveAll(vocabularyMediumList);
        } else {
            vocabularyMediumRepository.save(vocabularyMedium);
        }
    }

    @Override
    public void addVocabularyMediumList(List<VocabularyMediumReq> vocabularyMediumReqList) {
        String email = EmailUtils.getCurrentUser();
        if (ObjectUtils.isEmpty(email)) {
            throw new BusinessLogicException();
        }

        List<VocabularyMedium> vocabularyMediumList = vocabularyMediumMapper.toEntityList(vocabularyMediumReqList);
        for (VocabularyMedium medium : vocabularyMediumList) {
            if (medium.isPrimary()) {
                List<VocabularyMedium> vocabularyMediumListByVocabId = vocabularyMediumRepository.findAllByVocabularyId(medium.getVocabulary().getId());
                for (VocabularyMedium medium1 : vocabularyMediumListByVocabId) {
                    medium1.setPrimary(false);
                }
                vocabularyMediumListByVocabId.add(medium);
                vocabularyMediumRepository.saveAll(vocabularyMediumListByVocabId);
            } else {
                vocabularyMediumRepository.save(medium);
            }
        }
    }

    @Override
    public void updateVocabularyMedium(UpdateVocabularyMediumReq updateVocabularyMediumReq) {
        String email = EmailUtils.getCurrentUser();
        if (ObjectUtils.isEmpty(email)) {
            throw new BusinessLogicException();
        }

        VocabularyMedium vocabularyMedium = vocabularyMediumRepository.findById(updateVocabularyMediumReq.getVocabularyMediumId()).orElseThrow(BusinessLogicException::new);

        // neu là true thi set toan bo con lai la false
        if (updateVocabularyMediumReq.isPrimary()) {
            List<VocabularyMedium> vocabularyMediumList = vocabularyMediumRepository.findAllByVocabularyId(vocabularyMedium.getVocabulary().getId());
            for (VocabularyMedium medium : vocabularyMediumList) {
                medium.setPrimary(false);
            }
            vocabularyMediumList.add(vocabularyMedium);
            vocabularyMediumRepository.saveAll(vocabularyMediumList);
        } else {
            if (updateVocabularyMediumReq.getImageLocation() != null)
                vocabularyMedium.setImageLocation(updateVocabularyMediumReq.getImageLocation());
            if (updateVocabularyMediumReq.getVideoLocation() != null)
                vocabularyMedium.setVideoLocation(updateVocabularyMediumReq.getVideoLocation());
        }

        vocabularyMediumRepository.save(vocabularyMedium);
    }

    @Override
    public void setPrimaryForVocabularyMedium(SetPrimaryForVocabularyMedium setPrimaryForVocabularyMedium) {
        String email = EmailUtils.getCurrentUser();
        if (ObjectUtils.isEmpty(email)) {
            throw new BusinessLogicException();
        }

        VocabularyMedium vocabularyMedium = vocabularyMediumRepository.findById(setPrimaryForVocabularyMedium.getVocabularyMediumId()).orElseThrow(BusinessLogicException::new);

        if (setPrimaryForVocabularyMedium.isPrimary()) {
            List<VocabularyMedium> vocabularyMediumList = vocabularyMediumRepository.findAllByVocabularyId(vocabularyMedium.getVocabulary().getId());
            for (VocabularyMedium medium : vocabularyMediumList) {
                medium.setPrimary(false);
            }
            vocabularyMediumList.add(vocabularyMedium);
            vocabularyMediumRepository.saveAll(vocabularyMediumList);

            // luu
            vocabularyMedium.setPrimary(true);
            vocabularyMediumRepository.save(vocabularyMedium);
        }
    }

    @Override
    public void deleteById(long id) {
        String email = EmailUtils.getCurrentUser();
        if (ObjectUtils.isEmpty(email)) {
            throw new BusinessLogicException();
        }

        vocabularyMediumRepository.deleteById(id);
    }
}
