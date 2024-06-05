package com.example.hust_learning_server.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SearchExamForUserReq {

    private long topicId;

    private boolean isPrivate;

    private String nameSearch;

    private Integer page = 0;

    private Integer size = 10;

    private boolean ascending = true;

    private String orderBy;

}
