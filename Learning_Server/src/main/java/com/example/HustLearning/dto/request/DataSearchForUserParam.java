package com.example.HustLearning.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DataSearchForUserParam {
  public  int page =1 ;
  public  int size =10;

  public String topic;
  public String vocabulary;

  public  boolean ascending = false;
  public String orderBy;
  public String createdFrom;
  public String createdTo;
  public int status;
}
