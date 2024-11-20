package com.example.hust_learning_server.entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "part_video")
@Entity
@AttributeOverride(name = "id", column = @Column(name = "part_video_id"))
public class PartVideo extends BaseEntity{

    private String videoLocation;

    private Long partId;
}
