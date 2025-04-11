package com.example.user_server.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserSchema{
    private String userId;
    private String createdBy;
    private String createdDate;
    private String modifiedBy;
    private String modifiedDate;
    private String address;
    private String avatarLocation;
    private String birthDay;
    private String email;
    private String gender;
    private String name;
    private String password;
    private String phoneNumber;
    private String code;
}

