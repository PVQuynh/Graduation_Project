package com.chat.websocket.entity;

import com.chat.websocket.enum_constant.ConversationType;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "conversation")
@Builder
public class Conversation extends  BaseEntity{
    @Column(name = "conversation_name")
    private String conversationName;

    @Enumerated(EnumType.STRING)
    private ConversationType conversationType;

    @OneToMany(mappedBy = "conversation",
            fetch = FetchType.LAZY,
            cascade = {CascadeType.PERSIST},
            orphanRemoval = true)
    private List<GroupMember> groupMembers;



}
