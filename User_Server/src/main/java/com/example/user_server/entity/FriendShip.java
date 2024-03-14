package com.example.user_server.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.Date;

@Entity
@Table(name = "friend_ship")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EntityListeners(AuditingEntityListener.class)
@IdClass(FriendShipId.class)
public class FriendShip {

    @Id
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "send_friend_id")
    private User sendFriend;

    @Id
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "accept_friend_id")
    private User acceptFriend;

    private int status;

    @Column(name = "created_date", nullable = false)
    @CreatedDate
    private Date created;

    @Column(name = "modified_date")
    @LastModifiedDate
    private Date modified;

    @Column(name = "created_by", nullable = false)
    @CreatedBy
    private String author;

    @Column(name = "modified_by")
    @LastModifiedDate
    private String editor;
}
