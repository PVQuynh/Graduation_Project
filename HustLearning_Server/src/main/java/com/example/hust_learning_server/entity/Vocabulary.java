package com.example.hust_learning_server.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "vocabulary")
@Entity
@AttributeOverride(name = "id", column = @Column(name = "vocabulary_id"))
public class Vocabulary extends BaseEntity{

    private String content;

    private String imageLocation;

    private  String videoLocation;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="topic_id")
    private Topic topic;
}
