package com.example.hust_learning_server.service;

import com.example.hust_learning_server.dto.request.*;

public interface VocabularyMediumService {

    void addVocabularyMedium(VocabularyMediumReq vocabularyMediumReq);

    void updateVocabularyMedium(UpdateVocabularyMediumReq updateVocabularyMediumReq);

    void deleteById(long id);

}
