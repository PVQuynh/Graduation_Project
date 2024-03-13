package com.example.HustLearning.service;

import com.example.HustLearning.dto.PageDTO;
import com.example.HustLearning.dto.request.SearchParamReq;
import com.example.HustLearning.dto.request.UpdateVocabReq;
import com.example.HustLearning.dto.request.VocabReq;
import com.example.HustLearning.dto.response.VocabRes;
import com.example.HustLearning.entity.Vocabulary;

import java.util.List;

public interface VocabularySerivce {

    List<VocabRes> getVocabulariesByTopicId(long topicId);

    PageDTO<VocabRes> search(SearchParamReq searchParamReq);

    void addVocabulary(VocabReq vocabulary);

    void updateVocabulary(UpdateVocabReq updateVocabReq);

    void deleteById(long id);

}
