package com.example.UserService.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegisterReq implements Serializable{
    @NotBlank
    private String name;
    @Email
    private String email;
    @NotBlank
    private String password;


    private String role;


}
