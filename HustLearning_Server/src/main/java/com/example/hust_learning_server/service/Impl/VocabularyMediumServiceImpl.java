package com.example.hust_learning_server.service.Impl;

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

@Service
@RequiredArgsConstructor
public class VocabularyMediumServiceImpl implements VocabularyMediumService{

    private final VocabularyMediumRepository vocabularyMediumRepository;

    private final VocabularyMediumMapper vocabularyMediumMapper;


    @Override
    public void addVocabularyMedium(VocabularyMediumReq vocabularyMediumReq) {
        String email = EmailUtils.getCurrentUser();
        if (ObjectUtils.isEmpty(email)) {
            throw new BusinessLogicException();
        }

        VocabularyMedium vocabularyMedium = vocabularyMediumMapper.toEntity(vocabularyMediumReq);
        vocabularyMediumRepository.save(vocabularyMedium);
    }

    @Override
    public void updateVocabularyMedium(UpdateVocabularyMediumReq updateVocabularyMediumReq) {
        String email = EmailUtils.getCurrentUser();
        if (ObjectUtils.isEmpty(email)) {
            throw new BusinessLogicException();
        }

        VocabularyMedium vocabularyMedium = vocabularyMediumRepository.findById(updateVocabularyMediumReq.getVocabularyMediumId()).orElseThrow(BusinessLogicException::new);
        if (updateVocabularyMediumReq.getImageLocation() != null) vocabularyMedium.setImageLocation(updateVocabularyMediumReq.getImageLocation());
        if (updateVocabularyMediumReq.getVideoLocation() != null) vocabularyMedium.setVideoLocation(updateVocabularyMediumReq.getVideoLocation());
        vocabularyMedium.setPrimary(updateVocabularyMediumReq.isPrimary());

        vocabularyMediumRepository.save(vocabularyMedium);
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
