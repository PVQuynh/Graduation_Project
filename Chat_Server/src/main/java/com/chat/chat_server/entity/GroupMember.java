package com.chat.chat_server.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "group_member")
@Builder
@AttributeOverride(name = "id", column = @Column(name = "group_member_id"))
public class GroupMember extends BaseEntity {

    private LocalDateTime lastActivity;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="contact_id")
    private Contact contact;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="conversation_id")
    private Conversation conversation;

    @OneToMany(mappedBy = "groupMember",
            fetch = FetchType.LAZY,
            cascade = {CascadeType.ALL})
    private List<Message> messages;
}
