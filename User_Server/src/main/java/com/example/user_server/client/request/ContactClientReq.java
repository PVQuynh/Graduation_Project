package com.example.user_server.client.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ContactClientReq {

    public String name;

    public String email;

    public String avatarLocation;
}
