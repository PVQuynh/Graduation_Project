package com.example.UserService.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoleReq {
    private  String name;
    private  String description;
    private  String code;
}
