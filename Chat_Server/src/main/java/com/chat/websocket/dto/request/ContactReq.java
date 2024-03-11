package com.chat.websocket.dto.request;

import com.chat.websocket.entity.Contact;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ContactReq {

    public String name;

    public String email;

    public String avatarLocation;

    public ContactReq(Contact contact) {
        this.name = contact.getName();
        this.email = contact.getEmail();
        this.avatarLocation = contact.getAvatarLocation();
    }

}
