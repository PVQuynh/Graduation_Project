package com.example.data_collection_server.entity;

import com.example.data_collection_server.enum_constant.DataType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.util.Date;

@Entity
@Table(name = "data_collection")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@AttributeOverride(name = "id", column = @Column(name = "data_collection_id"))
public class DataCollection extends BaseEntity{

    private String content;

    @Enumerated(EnumType.STRING)
    public DataType dataType;

    private String dataLocation;

    private String volunteerEmail;

    private String adminEmail;

    private int status;

    private String feedBack;

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
    @LastModifiedDate
    private String editor;
}