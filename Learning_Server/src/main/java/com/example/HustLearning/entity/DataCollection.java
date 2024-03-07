package com.example.HustLearning.entity;


import com.example.HustLearning.entity.BaseEntity;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
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
public class DataCollection extends BaseEntity {
    private String dataLocation;

    private String volunteerEmail;

    private String adminEmail;

    private int status;

    private String feedBack;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="vocab_id")
    private Vocabulary vocab;

}
