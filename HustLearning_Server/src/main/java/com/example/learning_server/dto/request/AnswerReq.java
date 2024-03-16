package com.example.learning_server.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AnswerReq {

    private String content;

    private boolean isCorrect;

    private String imageLocation;

    private String videoLocation;

    private long questionId;

}
