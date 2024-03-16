package com.example.hust_learning_server.entity;


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
@AttributeOverrides({
        @AttributeOverride(name = "id", column = @Column(name = "data_collection_id")),
        @AttributeOverride(name = "author", column = @Column(name = "volunteer_email"))
})
public class DataCollection extends BaseEntity {

    private String dataLocation;

    private String adminEmail;

    private int status;

    private String feedBack;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="vocabulary_id")
    private Vocabulary vocabulary;

}
