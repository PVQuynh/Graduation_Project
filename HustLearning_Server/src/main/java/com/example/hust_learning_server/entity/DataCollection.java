package com.example.hust_learning_server.entity;


import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.*;

@Getter
@Setter
@Entity
@Table(name = "data_collection")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@AttributeOverrides({
        @AttributeOverride(name = "id", column = @Column(name = "data_collection_id")),
        @AttributeOverride(name = "createdBy", column = @Column(name = "volunteer_email"))
})
public class DataCollection extends BaseEntity {

    private String dataLocation;

    private String adminEmail;

    private int status;

    private float score;

    private String feedBack;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="vocabulary_id")
    private Vocabulary vocabulary;

}
