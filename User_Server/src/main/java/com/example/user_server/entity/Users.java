package com.example.user_server.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users")
@Builder
public class Users {

    @Id
    @Column(name = "user_id", nullable = false)
    private Long userId;

    @Column(name = "created_by")
    private String createdBy;

    @Column(name = "created_date")
    private Timestamp createdDate;

    @Column(name = "modified_by")
    private String modifiedBy;

    @Column(name = "modified_date")
    private Timestamp modifiedDate;

    @Column(name = "address")
    private String address;

    @Column(name = "avatar_location")
    private String avatarLocation;

    @Column(name = "birth_day")
    private Timestamp birthDay;

    @Column(name = "email")
    private String email;

    @Column(name = "gender", length = 10)
    private String gender;

    @Column(name = "name")
    private String name;

    @Column(name = "password")
    private String password;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "code")
    private String code;
}

