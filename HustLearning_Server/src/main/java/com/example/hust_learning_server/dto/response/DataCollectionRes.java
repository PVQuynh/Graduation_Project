package com.example.hust_learning_server.dto.response;

import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DataCollectionRes {

  private long dataCollectionId;

  private String dataLocation;

  private String volunteerEmail;

  private String adminEmail;

  private int status;

  private float score;

  private String feedBack;

  private Date created;

  private Date modified;

  private String editor;

  private long vocabularyId;

  private String vocabularyContent;
}
