package com.example.HustLearning.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class VocabRes {
    private long id;

    private String content;

    private String imageLocation;

    private  String videoLocation;

    private  long topic_id;

}
