package com.example.hust_learning_server.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "class_room")
@Entity
@AttributeOverride(name = "id", column = @Column(name = "class_room_id"))
public class ClassRoom extends BaseEntity{

    private String content;

    private String imageLocation;

    @OneToMany(
            mappedBy = "classRoom",
            fetch = FetchType.LAZY,
            cascade = {CascadeType.ALL})
    private List<Topic> topics;

}
