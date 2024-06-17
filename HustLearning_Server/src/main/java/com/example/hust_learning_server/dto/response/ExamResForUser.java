package com.example.hust_learning_server.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ExamResForUser {

    private long examId;

    private String name;

    private long numberOfQuestions;

    private float score;

    private boolean isFinish;

    private Long classRoomId;

}
