package com.example.hust_learning_server.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UpdateTopicReq {

    private long topicId;

    private String content;

    private String imageLocation;

    private  String videoLocation;

    private boolean isPrivate;

    private long classRoomId;

}
