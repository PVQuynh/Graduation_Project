package com.example.hust_learning_server.entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "part_image")
@Entity
@AttributeOverride(name = "id", column = @Column(name = "part_image_id"))
public class PartImage extends BaseEntity{

    private String imageLocation;

    private Long partId;
}
