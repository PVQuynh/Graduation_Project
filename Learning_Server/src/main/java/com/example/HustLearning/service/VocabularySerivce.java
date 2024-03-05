package com.example.HustLearning.service;

import com.example.HustLearning.dto.PageDTO;
import com.example.HustLearning.dto.request.SearchParamReq;
import com.example.HustLearning.dto.request.VocabReq;
import com.example.HustLearning.dto.response.VocabRes;
import com.example.HustLearning.entity.Vocabulary;

import java.util.List;

public interface VocabularySerivce {



    void addVocabulary(VocabReq vocabulary);



    List<VocabRes> getVocabulariesByTopicId(long topicId);


    void deleteById(long id);

    PageDTO<VocabRes> search(SearchParamReq searchParamReq);
}
