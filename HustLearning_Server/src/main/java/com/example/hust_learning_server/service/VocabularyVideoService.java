package com.example.hust_learning_server.service;

import com.example.hust_learning_server.dto.request.SetPrimaryForVocabularyVideo;
import com.example.hust_learning_server.dto.request.UpdateVocabularyVideoReq;
import com.example.hust_learning_server.dto.request.VocabularyVideoReq;

import java.util.List;

public interface VocabularyVideoService {

    void addVocabularyVideo(VocabularyVideoReq vocabularyVideoReq);

    void addVocabularyVideoList(List<VocabularyVideoReq> vocabularyVideoReqList);

    void updateVocabularyVideo(UpdateVocabularyVideoReq updateVocabularyVideoReq);

    void setPrimaryForVocabularyVideo(SetPrimaryForVocabularyVideo setPrimaryForVocabularyVideo);

    void deleteById(long id);

}
