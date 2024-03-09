package com.chat.websocket.dto.response;

import com.chat.websocket.entity.GroupMember;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GroupMemberRes {
    private LocalDateTime lastActivity;

    private Date joinTime;

    private boolean isActive;

    public GroupMemberRes(GroupMember groupMember) {
        this.lastActivity = groupMember.getLastActivity();
        this.isActive = groupMember.isActive();
    }
}
