package com.example.user_server.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Role {
    @Id
    private String code;

    @NotBlank
    private String name;

    private  String description;

    public static final String ADMIN = "ADMIN";
    public static final String TEACHER = "TEACHER";
    public static final String USER = "USER";

}
