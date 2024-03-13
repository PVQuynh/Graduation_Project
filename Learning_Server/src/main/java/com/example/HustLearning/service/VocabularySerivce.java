package com.example.HustLearning.service;

import com.example.HustLearning.dto.PageDTO;
import com.example.HustLearning.dto.request.*;
import com.example.HustLearning.dto.response.VocabularyRes;

import java.util.List;

public interface VocabularySerivce {

    List<VocabularyRes> getVocabulariesByTopicId(long topicId);

    List<VocabularyRes> vocabularyLimits(VocabularyLimitReq vocabularyLimitReq);

    PageDTO<VocabularyRes> search(SearchParamReq searchParamReq);

    void addVocabulary(VocabularyReq vocabulary);

    void updateVocabulary(UpdateVocabularyReq updateVocabularyReq);

    void deleteById(long id);

}
