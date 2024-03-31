package com.example.hust_learning_server.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class VocabularyMediumRes {

    private long vocabularyMediumId;

    private String imageLocation;

    private String videoLocation;

    private boolean primary;

    private long vocabularyId;

    private String vocabularyContent;

}
