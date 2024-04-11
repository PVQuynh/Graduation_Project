package com.example.hust_learning_server.service;

import com.example.hust_learning_server.dto.request.*;

import java.util.List;

public interface VocabularyMediumService {

    void addVocabularyMedium(VocabularyMediumReq vocabularyMediumReq);

    void addVocabularyMediumList(List<VocabularyMediumReq> vocabularyMediumReqList);

    void updateVocabularyMedium(UpdateVocabularyMediumReq updateVocabularyMediumReq);

    void deleteById(long id);

}
