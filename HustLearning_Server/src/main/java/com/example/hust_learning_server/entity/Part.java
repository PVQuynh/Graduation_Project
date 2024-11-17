package com.example.hust_learning_server.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "part")
@Entity
@AttributeOverride(name = "id", column = @Column(name = "part_id"))
public class Part extends BaseEntity{

    private String partName;

    private String imageLocation;

    private  String videoLocation;

    private Long lessonId;
}
