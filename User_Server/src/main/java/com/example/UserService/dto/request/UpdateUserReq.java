package com.example.UserService.dto.request;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateUserReq {

    private String name;

    private String phoneNumber;

    private String address;

    private String birthDay;

    private String gender;
}
