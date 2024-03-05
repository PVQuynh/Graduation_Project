package com.example.DataCollection.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DataProvideReq {
    private String dataLocation;
    private String content;
    private String dataType;
}
