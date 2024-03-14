package com.chat.chat_server.entity;

import com.chat.chat_server.enum_constant.MessageType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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

