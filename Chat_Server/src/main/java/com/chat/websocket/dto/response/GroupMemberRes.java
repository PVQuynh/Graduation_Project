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

    private long groupMemberId;

    private LocalDateTime lastActivity;

    private Date joinTime;

    private boolean isActive;

    private long contactId;

    public GroupMemberRes(GroupMember groupMember) {
        this.groupMemberId = groupMember.getId();
        this.lastActivity = groupMember.getLastActivity();
        this.joinTime = groupMember.getCreated();
        this.isActive = groupMember.isActive();
        this.contactId = groupMember.getContact().getId();
    }


}
