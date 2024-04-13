package com.example.hust_learning_server.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class VocabularyImageRes {

    private long vocabularyImageId;

    private String imageLocation;

    private boolean isPrimary;

    private long vocabularyId;

    private String vocabularyContent;

}
