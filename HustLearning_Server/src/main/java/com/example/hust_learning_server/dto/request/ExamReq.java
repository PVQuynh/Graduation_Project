package com.example.hust_learning_server.dto.request;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ExamReq {

    private String name;

    private boolean isPrivate;

    private List<Long> questionIds;

    private long topicId;
}
