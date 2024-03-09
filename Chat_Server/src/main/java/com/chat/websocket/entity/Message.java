package com.chat.websocket.entity;

import com.chat.websocket.enum_constant.MessageType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "message")
@Builder
@AttributeOverride(name = "id", column = @Column(name = "message_id"))
public class Message extends BaseEntity{

    private String content;

    @Enumerated(EnumType.STRING)
    private MessageType messageType;

    private String mediaLocation;

    private int status;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="group_member_id")
    private GroupMember groupMember;



}

