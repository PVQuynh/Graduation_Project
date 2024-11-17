package com.example.hust_learning_server.dto.request;

import com.example.hust_learning_server.constant.enum_constant.VocabularyType;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PartReq {
    private String partName;

    private String imageLocation;

    private  String videoLocation;

    private Long lessonId;

}
