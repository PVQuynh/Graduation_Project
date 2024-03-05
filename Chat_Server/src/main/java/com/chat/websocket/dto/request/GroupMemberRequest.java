package com.chat.websocket.dto.request;


import com.chat.websocket.entity.GroupMember;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GroupMemberRequest {

    private int groupMemberID;
    private String firstName;
    private String lastName;
    private String avatarLocation;

   
}
