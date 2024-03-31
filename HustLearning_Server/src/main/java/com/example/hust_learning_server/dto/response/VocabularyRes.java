package com.example.hust_learning_server.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class VocabularyRes {

    private long vocabularyId;

    private String content;

    private List<VocabularyMediumRes> vocabularyMediumRes;

    private long topicId;

    private String topicContent;

}
