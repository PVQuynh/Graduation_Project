package com.chat.websocket.service;

import com.chat.websocket.dto.request.AddMemberReq;
import com.chat.websocket.entity.GroupMember;
import java.util.List;

public interface GroupMemberService {

  void add(AddMemberReq addmemberReq);

  void saveAll(List<GroupMember> groupMembers);

  void deleteMember(long groupMemberId);

  void save(GroupMember groupMember);
}
