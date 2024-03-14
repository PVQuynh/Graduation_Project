package com.chat.chat_server.entity;

import com.chat.chat_server.dto.request.ContactReq;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "contact")
@Builder
@AttributeOverride(name = "id", column = @Column(name = "contact_id"))
public class Contact extends  BaseEntity{

    private String name;

    private String email;

    private String avatarLocation;

    @OneToMany(mappedBy = "contact",
            fetch = FetchType.LAZY,
            cascade = {CascadeType.ALL})
    private List<GroupMember> groupMembers;

    public Contact(ContactReq contactReq) {
       this.name = contactReq.name;
        this.email = contactReq.email;
        this.avatarLocation = contactReq.avatarLocation;
    }
}
