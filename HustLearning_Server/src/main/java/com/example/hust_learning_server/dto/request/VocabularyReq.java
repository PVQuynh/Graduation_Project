package com.example.hust_learning_server.dto.request;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import com.example.hust_learning_server.constant.enum_constant.VocabularyType;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class VocabularyReq {

    private String content;

    private String note;

    @Enumerated(EnumType.STRING)
    private VocabularyType vocabularyType;

    private List<VocabularyImageReq> vocabularyImageReqs;

    private List<VocabularyVideoReq> vocabularyVideoReqs;

    private boolean isPrivate;

    private  long topicId;

}
