package com.example.hust_learning_server.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PartRes {

    private long partId;

    private String partName;

    private List<PartImageRes> partImageResList;

    private List<PartVideoRes> partVideoResList;

    private Long lessonId;
}
