package com.example.user_server.dto.response;

import com.example.user_server.entity.Users;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DataResponse {
    @JsonProperty("data_types")
    public Object dataTypes;

    public List<Users> records;

}
