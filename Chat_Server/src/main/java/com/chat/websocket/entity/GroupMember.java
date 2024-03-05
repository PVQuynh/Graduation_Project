package com.chat.websocket.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "group_member")
@Builder
public class GroupMember extends BaseEntity {

    @Column(name = "last_activity")
    private LocalDateTime lastActivity;

    @Column(name = "join_time")
    private LocalDateTime joinTime;

    @Column(name = "left_time")
    private LocalDateTime leftTime;
    @Column(name = "is_active")
    private boolean isActive;


    @ManyToOne
    @JoinColumn(name="contact_id")
    private Contact contact;


    @ManyToOne
    @JoinColumn(name="conversation_id")
    private Conversation conversation;


    @OneToMany(mappedBy = "groupMember",
            fetch = FetchType.LAZY,
            cascade = {CascadeType.PERSIST},
            orphanRemoval = true)
    private List<Message> messages;



    public GroupMember(LocalDateTime joinTime, Contact contact, Conversation conversation) {
        this.joinTime = joinTime;
        this.contact = contact;
        this.conversation = conversation;
    }
}
