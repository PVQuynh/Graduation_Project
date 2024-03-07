package com.chat.websocket.entity;

import com.chat.websocket.dto.request.ContactRequest;
import com.fasterxml.jackson.annotation.JsonIgnore;
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

    @Column(name = "first_name")
    private String name;

    @Column(name = "email")
    private String email;

    @Column(name = "avatar_location")
    private String avatarLocation;

    @OneToMany(mappedBy = "contact",
            fetch = FetchType.LAZY,
            cascade = {CascadeType.ALL})
    private List<GroupMember> groupMembers;

    public Contact(ContactRequest contactRequest) {
       this.name = contactRequest.name;
        this.email = contactRequest.email;
        this.avatarLocation = contactRequest.avatarLocation;
    }
}
