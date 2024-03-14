package com.chat.chat_server.entity;

import com.chat.chat_server.enum_constant.ConversationType;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "conversation")
@Builder
@AttributeOverride(name = "id", column = @Column(name = "conversation_id"))
public class Conversation extends  BaseEntity{

    private String conversationName;

    @Enumerated(EnumType.STRING)
    private ConversationType conversationType;

    @OneToMany(mappedBy = "conversation",
            fetch = FetchType.LAZY,
            cascade = {CascadeType.ALL})
    private List<GroupMember> groupMembers;



}
