package com.example.UserService.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Entity
@Table
@Data
public class Role {
    @Id
    private String code;
    @NotBlank
    private String name;
    private  String description;
}
