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
public class ContactRequest {

    public String name;

    public String email;

    public String phoneNumber;

    public String avatarLocation;


}
