package com.example.HustLearning.dto.request;

import com.example.HustLearning.dto.AnswerDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class QuestionReq {

    private String content;

    private String explanation;

    private String imageLocation;

    private  String videoLocation;

    private long topic_id;

    private List<AnswerDTO> answerDTOS;
}
