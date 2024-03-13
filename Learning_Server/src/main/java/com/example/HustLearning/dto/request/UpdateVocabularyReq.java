package com.example.HustLearning.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UpdateVocabularyReq {

    private long vocabularyId;

    private String content;

    private String imageLocation;

    private  String videoLocation;
}
