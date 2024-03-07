package com.example.UserService.client.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ContacClientReq {

    public String name;

    public String email;

    public String avatarLocation;
}
