package com.example.hust_learning_server.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DataSearchForUserParamV3 {
  public  int page =1 ;
  public  int size =10;
  public long topicId;
  public long vocabularyId;
  public  boolean ascending = false;
  public String orderBy;
  public String createdFrom;
  public String createdTo;
  public int status;
  public float score;
}
