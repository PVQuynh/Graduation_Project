package com.chat.websocket.dto.response;

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


}
