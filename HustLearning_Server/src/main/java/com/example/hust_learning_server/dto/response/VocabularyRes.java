package com.example.hust_learning_server.dto.response;

import com.example.hust_learning_server.constant.enum_constant.VocabularyType;
import com.example.hust_learning_server.dto.request.VocabularyImageReq;
import com.example.hust_learning_server.dto.request.VocabularyVideoReq;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
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

    private String note;

    @Enumerated(EnumType.STRING)
    private VocabularyType vocabularyType;

    private List<VocabularyImageRes> vocabularyImageResList;

    private List<VocabularyVideoRes> vocabularyVideoResList;

    private long topicId;

    private String topicContent;

}
