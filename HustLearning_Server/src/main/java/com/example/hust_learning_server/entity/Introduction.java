package com.example.hust_learning_server.entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "introduction")
@Entity
@AttributeOverride(name = "id", column = @Column(name = "introduction_id"))
public class Introduction extends BaseEntity{
    @Lob  // Chỉ định trường này là loại dữ liệu lớn
    @Column(columnDefinition = "TEXT")
    private String title;

    @Lob  // Chỉ định trường này là loại dữ liệu lớn
    @Column(columnDefinition = "TEXT")
    private String body;

    @Lob  // Chỉ định trường này là loại dữ liệu lớn
    @Column(columnDefinition = "TEXT")
    private String footer;
}
