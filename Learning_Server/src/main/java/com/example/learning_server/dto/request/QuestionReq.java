package com.example.learning_server.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class QuestionReq {

    private String content;

    private String explanation;

    private String imageLocation;

    private  String videoLocation;

    private long topicId;

    private List<AnswerReq> answerReqs;
}
