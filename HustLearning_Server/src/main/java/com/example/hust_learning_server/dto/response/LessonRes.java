package com.example.hust_learning_server.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LessonRes {

    private long lessonId;

    private String lessonName;

    private String imageLocation;

    private  String videoLocation;

    private Long classRoomId;

    private String classRoomContent;

}
