package com.example.HustLearning.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AnswerDTO {

    private String content;

    private boolean isCorrect;

    private String imageLocation;

    private  String videoLocation;

}
