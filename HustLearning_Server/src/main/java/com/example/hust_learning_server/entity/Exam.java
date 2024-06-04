package com.example.hust_learning_server.entity;

import java.util.List;
import jakarta.persistence.AttributeOverride;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="topic_id")
    private Topic topic;
}
