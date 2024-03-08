package com.chat.websocket.service;

import com.chat.websocket.dto.request.GroupMemberReq;
import com.chat.websocket.entity.GroupMember;
import java.util.List;

public interface GroupMemberService {

  void save(GroupMemberReq groupMemberReq);

  void saveAll(List<GroupMember> groupMembers);

  void deleteMember(long id);


  //
  void save(GroupMember groupMember);
}
