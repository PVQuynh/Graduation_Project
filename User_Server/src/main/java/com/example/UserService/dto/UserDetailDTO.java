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
public class UserDetailDTO {
    private  long id;
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

    private  int friendStatus;

    public UserDetailDTO(UserDTO userDTO) {
        this.id = userDTO.getId();
        this.name = userDTO.getName();
        this.email = userDTO.getEmail();
        this.phoneNumber = userDTO.getPhoneNumber();
        this.address = userDTO.getAddress();
        this.role = userDTO.getRole();
        this.birthDay = userDTO.getBirthDay();
        this.gender = userDTO.getGender();
        this.avatarLocation = userDTO.getAvatarLocation();
    }
}
