package com.example.HustLearning.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UpdateQuestionReq {

    private long questionId;

    private String content;

    private String explanation;

    private String imageLocation;

    private  String videoLocation;
}
