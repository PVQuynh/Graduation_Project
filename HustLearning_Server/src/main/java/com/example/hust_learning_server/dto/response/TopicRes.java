package com.example.hust_learning_server.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TopicRes {

    private long topicId;

    private String content;

    private String imageLocation;

    private  String videoLocation;

    private long classRoomId;

    private String classRoomContent;

}
