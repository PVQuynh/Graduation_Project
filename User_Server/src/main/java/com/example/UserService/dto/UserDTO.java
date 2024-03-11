package com.example.UserService.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDTO {

    private long userId;

    @NotBlank
    private String name;
    @Email
    private String email;

    private String phoneNumber;

    private String address;

    private String role;

    private Date birthDay;

    private String gender;

    private  String avatarLocation;
}
