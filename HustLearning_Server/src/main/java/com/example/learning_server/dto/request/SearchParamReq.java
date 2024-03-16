package com.example.learning_server.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SearchParamReq {

    public  int page =1 ;
    public  int size =10;
    public String text;
    public  boolean ascending = false;
    public String orderBy;

    public long topicId;
}
