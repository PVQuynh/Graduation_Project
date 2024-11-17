package com.example.hust_learning_server.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "lesson")
@Entity
@AttributeOverride(name = "id", column = @Column(name = "lesson_id"))
@Builder
public class Lesson extends BaseEntity{

    private String lessonName;

    private String imageLocation;

    private  String videoLocation;

    private Long classRoomId;

}
