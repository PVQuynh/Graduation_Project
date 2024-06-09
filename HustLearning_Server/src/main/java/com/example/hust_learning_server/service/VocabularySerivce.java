package com.example.hust_learning_server.service;

import com.example.hust_learning_server.dto.PageDTO;
import com.example.hust_learning_server.dto.request.*;
import com.example.hust_learning_server.dto.response.VocabularyRes;

import java.util.List;

public interface VocabularySerivce {
    VocabularyRes getById(long id);

    List<VocabularyRes> getAllVocabulary();

    List<VocabularyRes> getAllVocabularies(long topicId, String vocabularyType, String contentSearch);

    List<VocabularyRes> getExactVocabularies(ExactVocabularyReq exactVocabularyReq);

    List<VocabularyRes> getVocabulariesByContent(String content);

    List<VocabularyRes> getVocabulariesByTopicId(long topicId);

    List<VocabularyRes> getVocabularyByTopicIdAndVocabularyTypeAndSearchContent(Long topicId, String vocabularyType, String content);

    List<VocabularyRes> vocabularyLimits(VocabularyLimitReq vocabularyLimitReq);

    List<VocabularyRes> vocabularyLimitsTopic(int page, int size, long topicId);

    PageDTO<VocabularyRes> search(SearchVocabularyParamReq searchVocabularyParamReq);

    PageDTO<VocabularyRes> searchV2(int page, int size, String text, boolean ascending, String orderBy, long topicId);

    void addVocabulary(VocabularyReq vocabulary);

    void addVocabularyToNewTopic(AddVocabularyToNewTopic addVocabularyToNewTopic);

    void addVocabularyListToNewTopic(List<AddVocabularyToNewTopic> addVocabularyToNewTopicList);

    void addVocabularyListToNewTopic_v2(AddVocabularyListToNewTopic addVocabularyListToNewTopic);

    void addVocabularyList(List<VocabularyReq> vocabularyReqList);

    void updateVocabulary(UpdateVocabularyReq updateVocabularyReq);

    void deleteById(long id);

    void deleteAllById(DeleteVocabulariesReq deleteVocabulariesReq);

}
