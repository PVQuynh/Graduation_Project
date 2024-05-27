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
public class VocabularyReq {

    private String content;

    private String note;

    private List<VocabularyImageReq> vocabularyImageReqs;

    private List<VocabularyVideoReq> vocabularyVideoReqs;

    private  long topicId;

}
