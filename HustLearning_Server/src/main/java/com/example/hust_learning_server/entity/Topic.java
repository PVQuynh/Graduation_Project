package com.example.hust_learning_server.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "topic")
@Entity
@AttributeOverride(name = "id", column = @Column(name = "topic_id"))
@Builder
public class Topic extends BaseEntity{

    private String content;

    private String imageLocation;

    private  String videoLocation;

    private boolean isPrivate;

    @OneToMany(mappedBy = "topic",
            fetch = FetchType.LAZY,
            cascade = {CascadeType.ALL})
    private List<Vocabulary> vocabularies;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="class_room_id")
    private ClassRoom classRoom;

}
