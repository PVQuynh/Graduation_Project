package com.example.hust_learning_server.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class VocabularyCollectionRes {

    private String content;

    private String imageLocation;

    private String videoLocation;
}
