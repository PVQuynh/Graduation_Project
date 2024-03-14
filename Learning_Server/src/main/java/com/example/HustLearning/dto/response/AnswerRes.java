package com.example.HustLearning.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AnswerRes {

    private long answerId;

    private String content;

    private boolean isCorrect;

    private String imageLocation;

    private String videoLocation;

    private long questionId;
}
