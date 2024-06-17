package com.example.hust_learning_server.dto.response;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import com.example.hust_learning_server.constant.enum_constant.FileType;
import com.example.hust_learning_server.constant.enum_constant.QuestionType;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class QuestionRes {

    private long questionId;

    private String content;

    private String explanation;

    private String imageLocation;

    private  String videoLocation;

    @Enumerated(EnumType.STRING)
    private QuestionType questionType;

    @Enumerated(EnumType.STRING)
    private FileType fileType;

    private Long classRoomId;

    private List<AnswerRes> answerResList;

}
