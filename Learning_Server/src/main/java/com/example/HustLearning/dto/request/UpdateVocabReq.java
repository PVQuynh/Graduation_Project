package com.example.HustLearning.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UpdateVocabReq {

    private long vocabularyId;

    private String content;

    private String imageLocation;

    private  String videoLocation;
}
