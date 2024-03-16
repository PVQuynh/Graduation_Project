package com.example.learning_server.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DataSearchForAdminParam {
  public  int page =1 ;
  public  int size =10;

  public  String volunteerEmail;
  public  boolean ascending = false;
  public String orderBy;
  public String createdFrom;
  public String createdTo;
  public int status;
  public String topic;
  public String vocabulary;

}
