package com.example.user_server.entity;

import com.example.user_server.enum_constant.Gender;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "user")
@Builder
@AttributeOverride(name = "id", column = @Column(name = "user_id"))
public class User extends  BaseEntity {

    @Email(message = "Email isn't valid")
    @Column(unique = true,nullable = false)
    private  String email;

    private String password;

    @Column(nullable = false)
    private  String name;

    private String address;

    private  String phoneNumber;

    private  String avatarLocation;

    private  boolean isOauth2;

    private Date birthDay;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    private boolean isApproved;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "code")
    private Role role;

}
