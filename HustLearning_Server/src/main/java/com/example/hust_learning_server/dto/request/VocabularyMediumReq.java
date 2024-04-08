package com.example.hust_learning_server.dto.request;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class VocabularyMediumReq {

    private String imageLocation;

    private String videoLocation;

    private boolean isPrimary = false;

    private long vocabularyId;

}
