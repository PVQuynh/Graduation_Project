package com.example.hust_learning_server.entity;

import jakarta.persistence.*;
import lombok.*;

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

    private Long lessonId;
}
