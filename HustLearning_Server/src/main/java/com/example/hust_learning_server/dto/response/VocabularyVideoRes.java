package com.example.hust_learning_server.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class VocabularyVideoRes {

    private long vocabularyVideoId;

    private String videoLocation;

    private boolean isPrimary;

    private long vocabularyId;

    private String vocabularyContent;

}
