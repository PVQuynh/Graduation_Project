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
public class SearchExamReq {

    private long topicId;

    private String isPrivate;

    private String nameSearch;

    private Integer page = 0;

    private Integer size = 10;

    private boolean ascending = true;

    private String orderBy;

}
