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
public class QuestionRes {

    private long questionId;

    private String content;

    private String explanation;

    private String imageLocation;

    private  String videoLocation;

    private boolean isPrivate;

    private long topicId;

    private List<AnswerRes> answerResList;

}
