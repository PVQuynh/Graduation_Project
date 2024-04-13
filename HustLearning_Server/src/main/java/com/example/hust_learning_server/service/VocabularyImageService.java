package com.example.hust_learning_server.service;

import com.example.hust_learning_server.dto.request.SetPrimaryForVocabularyImage;
import com.example.hust_learning_server.dto.request.UpdateVocabularyImageReq;
import com.example.hust_learning_server.dto.request.VocabularyImageReq;

import java.util.List;

public interface VocabularyImageService {

    void addVocabularyImage(VocabularyImageReq vocabularyImageReq);

    void addVocabularyImageList(List<VocabularyImageReq> vocabularyImageReqList);

    void updateVocabularyImage(UpdateVocabularyImageReq updateVocabularyImageReq);

    void setPrimaryForVocabularyImage(SetPrimaryForVocabularyImage setPrimaryForVocabularyImage);

    void deleteById(long id);

}
