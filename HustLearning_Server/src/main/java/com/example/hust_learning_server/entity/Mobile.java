package com.example.hust_learning_server.entity;

import jakarta.persistence.AttributeOverride;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "mobile")
@Entity
@AttributeOverride(name = "id", column = @Column(name = "mobile_id"))
public class Mobile extends BaseEntity{
    private String mobileLocation;
}
