package com.chat.websocket.entity;

import com.chat.websocket.dto.request.MessageRequest;
import com.chat.websocket.enum_constant.MessageType;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "message")
@Builder
@AttributeOverride(name = "id", column = @Column(name = "message_id"))
public class Message extends BaseEntity{

    @Column(name = "content")
    private String content;

    @Column(name = "message_type")
    private MessageType messageType;

    @Column(name = "media_location")
    private String mediaLocation;

    @Column(name = "status")
    private int status;

    @CreationTimestamp
    @Column(name = "creation_time")
    private LocalDateTime creationTime;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="group_member_id")
    private GroupMember groupMember;



}

