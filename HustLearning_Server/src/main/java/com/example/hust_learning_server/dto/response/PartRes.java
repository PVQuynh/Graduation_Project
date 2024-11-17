package com.example.hust_learning_server.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PartRes {

    private Long partId;

    private String partName;

    private String imageLocation;

    private  String videoLocation;

    private Long lessonId;

}
