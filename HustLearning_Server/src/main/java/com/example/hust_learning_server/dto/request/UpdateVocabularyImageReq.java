package com.example.hust_learning_server.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UpdateVocabularyImageReq {

    private long vocabularyImageId;

    private String imageLocation;

    private boolean isPrimary;


}
