package com.example.learning_server.service;

import com.example.learning_server.dto.PageDTO;
import com.example.learning_server.dto.request.*;
import com.example.learning_server.dto.response.VocabularyRes;
import com.example.learning_server.exception.BusinessLogicException;

import java.util.List;

public interface VocabularySerivce {

    List<VocabularyRes> getVocabulariesByTopicId(long topicId);

    List<VocabularyRes> vocabularyLimits(VocabularyLimitReq vocabularyLimitReq);

    PageDTO<VocabularyRes> search(SearchParamReq searchParamReq);

    void addVocabulary(VocabularyReq vocabulary);

    void updateVocabulary(UpdateVocabularyReq updateVocabularyReq);

    void deleteById(long id);

}
