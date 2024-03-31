package com.example.hust_learning_server.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UpdateVocabularyReq {

    private long vocabularyId;

    private String content;

    private List<VocabularyMediumReq> vocabularyMediumReqs;
}
