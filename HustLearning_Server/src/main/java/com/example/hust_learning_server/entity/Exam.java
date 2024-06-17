package com.example.hust_learning_server.entity;

import jakarta.persistence.AttributeOverride;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "exam")
@Entity
@AttributeOverride(name = "id", column = @Column(name = "exam_id"))
public class Exam extends BaseEntity{

    private String name;

    private boolean isPrivate;

    private Long classRoomId;
}
