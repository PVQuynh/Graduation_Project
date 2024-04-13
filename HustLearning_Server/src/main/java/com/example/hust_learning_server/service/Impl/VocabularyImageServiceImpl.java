package com.example.hust_learning_server.service.Impl;

import com.example.hust_learning_server.dto.request.SetPrimaryForVocabularyImage;
import com.example.hust_learning_server.dto.request.UpdateVocabularyImageReq;
import com.example.hust_learning_server.dto.request.VocabularyImageReq;
import com.example.hust_learning_server.entity.VocabularyImage;
import com.example.hust_learning_server.exception.AlreadyExistsException;
import com.example.hust_learning_server.exception.BusinessLogicException;
import com.example.hust_learning_server.mapper.VocabularyImageMapper;
import com.example.hust_learning_server.repository.VocabularyImageRepository;
import com.example.hust_learning_server.service.VocabularyImageService;
import com.example.hust_learning_server.utils.AvoidRepetition;
import com.example.hust_learning_server.utils.EmailUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class VocabularyImageServiceImpl implements VocabularyImageService {

    private final VocabularyImageRepository vocabularyImageRepository;

    private final VocabularyImageMapper vocabularyImageMapper;


    @Override
    public void addVocabularyImage(VocabularyImageReq vocabularyImageReq) {
        String email = EmailUtils.getCurrentUser();
        if (ObjectUtils.isEmpty(email)) {
            throw new BusinessLogicException();
        }
        Optional<VocabularyImage> existingVocabularyImage = vocabularyImageRepository.findByImageLocationAndVocabularyId(vocabularyImageReq.getImageLocation(), vocabularyImageReq.getVocabularyId());

        if (existingVocabularyImage.isEmpty()) {
            VocabularyImage vocabularyImage = vocabularyImageMapper.toEntity(vocabularyImageReq);

            if (vocabularyImage.isPrimary()) {
                List<VocabularyImage> vocabularyImageList = vocabularyImageRepository.findAllByVocabularyId(vocabularyImageReq.getVocabularyId());
                for (VocabularyImage image : vocabularyImageList) {
                    image.setPrimary(false);
                }
                vocabularyImageList.add(vocabularyImage);
                vocabularyImageRepository.saveAll(vocabularyImageList);
            } else {
                vocabularyImageRepository.save(vocabularyImage);
            }
        } else {
            throw new AlreadyExistsException();
        }
    }

    @Override
    public void addVocabularyImageList(List<VocabularyImageReq> vocabularyImageReqList) {
        String email = EmailUtils.getCurrentUser();
        if (ObjectUtils.isEmpty(email)) {
            throw new BusinessLogicException();
        }

        // xu ly dau vao, ko bi lap location
        List<VocabularyImage> vocabularyImageList = AvoidRepetition.avoidRepeatingVocabularyImageLocation(vocabularyImageMapper.toEntityList(vocabularyImageReqList));

        // xu ly db, avoid tranh  bi chong lan tu
        List<VocabularyImage> nonOverlappingVocabularyList = new ArrayList<>();
        for (VocabularyImage image: vocabularyImageList) {
            Optional<VocabularyImage> existingVocabularyImage = vocabularyImageRepository.findByImageLocationAndVocabularyId(image.getImageLocation(), image.getVocabulary().getId());

            // ko ton tai thi bat dau them vao
            if (existingVocabularyImage.isEmpty()) {
                nonOverlappingVocabularyList.add(image);

                // neu la primary thi chuyen toan bo con lai ve false
                if (image.isPrimary()) {
                    List<VocabularyImage> vocabularyImageListByVocabId = vocabularyImageRepository.findAllByVocabularyId(image.getVocabulary().getId());
                    for (VocabularyImage image1 : vocabularyImageListByVocabId) {
                        image1.setPrimary(false);
                    }
                    vocabularyImageListByVocabId.add(image);
                    vocabularyImageRepository.saveAll(vocabularyImageListByVocabId);
                }
            }
        }

        vocabularyImageRepository.saveAll(nonOverlappingVocabularyList);
    }


    @Override
    public void updateVocabularyImage(UpdateVocabularyImageReq updateVocabularyImageReq) {
        String email = EmailUtils.getCurrentUser();
        if (ObjectUtils.isEmpty(email)) {
            throw new BusinessLogicException();
        }

        // lay ra tu db de update
        VocabularyImage vocabularyImage = vocabularyImageRepository.findById(updateVocabularyImageReq.getVocabularyImageId()).orElseThrow(BusinessLogicException::new);

        // neu update là true thi set toan bo con lai la false
        if (updateVocabularyImageReq.isPrimary()) {
            List<VocabularyImage> vocabularyImageList = vocabularyImageRepository.findAllByVocabularyId(vocabularyImage.getVocabulary().getId());
            for (VocabularyImage image : vocabularyImageList) {
                image.setPrimary(false);
            }

            // set primary cho image va update
            vocabularyImage.setPrimary(true);
            vocabularyImage.setImageLocation(updateVocabularyImageReq.getImageLocation());
            vocabularyImageRepository.saveAll(vocabularyImageList);
        } else {
            if (updateVocabularyImageReq.getImageLocation() != null)
                vocabularyImage.setImageLocation(updateVocabularyImageReq.getImageLocation());
            vocabularyImage.setPrimary(false);
            vocabularyImageRepository.save(vocabularyImage);
        }
    }

    @Override
    public void setPrimaryForVocabularyImage(SetPrimaryForVocabularyImage setPrimaryForVocabularyImage) {
        String email = EmailUtils.getCurrentUser();
        if (ObjectUtils.isEmpty(email)) {
            throw new BusinessLogicException();
        }

        VocabularyImage vocabularyImage = vocabularyImageRepository.findById(setPrimaryForVocabularyImage.getVocabularyImageId()).orElseThrow(BusinessLogicException::new);

        if (setPrimaryForVocabularyImage.isPrimary()) {
            List<VocabularyImage> vocabularyImageList = vocabularyImageRepository.findAllByVocabularyId(vocabularyImage.getVocabulary().getId());
            for (VocabularyImage image : vocabularyImageList) {
                image.setPrimary(false);
            }
            vocabularyImageList.add(vocabularyImage);
            vocabularyImageRepository.saveAll(vocabularyImageList);

            // luu
            vocabularyImage.setPrimary(true);
            vocabularyImageRepository.save(vocabularyImage);
        }
    }

    @Override
    public void deleteById(long id) {
        String email = EmailUtils.getCurrentUser();
        if (ObjectUtils.isEmpty(email)) {
            throw new BusinessLogicException();
        }

        vocabularyImageRepository.deleteById(id);
    }
}
