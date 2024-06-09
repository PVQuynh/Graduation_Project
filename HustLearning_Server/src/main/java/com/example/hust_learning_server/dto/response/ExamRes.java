package com.example.hust_learning_server.dto.response;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ExamRes {

    private long examId;

    private String name;

    private long numberOfQuestions;

    private boolean isPrivate;

    private long topicId;

}
