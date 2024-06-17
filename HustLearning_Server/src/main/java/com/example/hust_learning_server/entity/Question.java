package com.example.hust_learning_server.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import com.example.hust_learning_server.constant.enum_constant.FileType;
import com.example.hust_learning_server.constant.enum_constant.QuestionType;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "question")
@Entity
@AttributeOverride(name = "id", column = @Column(name = "question_id"))
public class Question extends BaseEntity {

    @Column(length = 2000)
    private String content;

    @Column(length = 2000)
    private String explanation;

    private String imageLocation;

    private String videoLocation;

    @Enumerated(EnumType.STRING)
    private QuestionType questionType;

    @Enumerated(EnumType.STRING)
    private FileType fileType;

    private Long classRoomId;

    @OneToMany(mappedBy = "question",
            cascade = {CascadeType.ALL},
            fetch = FetchType.LAZY)
    private List<Answer> answers;
}
