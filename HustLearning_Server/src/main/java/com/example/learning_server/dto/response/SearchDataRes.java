package com.example.learning_server.dto.response;

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

  private long dataCollectionId;

  private String dataLocation;

  private String volunteerEmail;

  private String adminEmail;

  private int status;

  private String feedBack;

  private Date created;

  private Date modified;

  private String editor;

  private long topicId;

  private String topicContent;

  private long vocabularyId;

  private String vocabularyContent;


}
