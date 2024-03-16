package com.example.hust_learning_server.service;

import com.example.hust_learning_server.dto.PageDTO;
import com.example.hust_learning_server.dto.request.*;
import com.example.hust_learning_server.dto.response.VocabularyRes;

import java.util.List;

public interface VocabularySerivce {

    List<VocabularyRes> getVocabulariesByTopicId(long topicId);

    List<VocabularyRes> vocabularyLimits(VocabularyLimitReq vocabularyLimitReq);

    PageDTO<VocabularyRes> search(SearchVocabularyParamReq searchVocabularyParamReq);

    void addVocabulary(VocabularyReq vocabulary);

    void updateVocabulary(UpdateVocabularyReq updateVocabularyReq);

    void deleteById(long id);

}
