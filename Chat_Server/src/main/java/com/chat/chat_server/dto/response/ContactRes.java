package com.chat.chat_server.dto.response;

import com.chat.chat_server.entity.Contact;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ContactRes {

  public long contactId;

  public String name;

  public String avatarLocation;

  public String email;

  public ContactRes(Contact contact) {
    this.contactId = contact.getId();
    this.name = contact.getName();
    this.avatarLocation = contact.getAvatarLocation();
    this.email = contact.getEmail();
  }
}
