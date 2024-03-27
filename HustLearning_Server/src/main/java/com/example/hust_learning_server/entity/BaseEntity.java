package com.example.hust_learning_server.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "created_date")
    @CreatedDate
    private Date created;

    @Column(name = "modified_date")
    @LastModifiedDate
    private Date modified;

    @Column(name = "created_by")
    @CreatedBy
    private String author;

    @Column(name = "modified_by")
    @LastModifiedBy
    private String editor;
}
