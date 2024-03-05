package com.example.HustLearning.dto.response;

import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class SearchDataRes {

  private long id;

  private String vocab;

  private String topic;

  private String dataLocation;

  private String volunteerEmail;

  private String adminEmail;

  private int status;

  private String feedBack;

  private Date created;

  private Date modified;

  private String author;

  private String editor;
}
