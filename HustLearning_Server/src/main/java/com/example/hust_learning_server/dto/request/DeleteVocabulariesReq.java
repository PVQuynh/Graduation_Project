package com.example.hust_learning_server.dto.request;

import java.util.List;
import com.example.hust_learning_server.constant.enum_constant.VocabularyType;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DeleteVocabulariesReq {

    private List<Long> vocabularyIds;

}
