package com.example.hust_learning_server.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ExamSavedReq {

    private long questionId;

    private long examId;

    private long[] selectedAnswers;


}
