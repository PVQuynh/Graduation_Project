package com.example.HustLearning.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@Entity
@Table(name = "data_collection")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@AttributeOverride(name = "id", column = @Column(name = "data_collection_id"))
public class DataCollection extends BaseEntity {

    private String dataLocation;

    private String volunteerEmail;

    private String adminEmail;

    private int status;

    private String feedBack;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="vocab_id")
    private Vocabulary vocab;

}
