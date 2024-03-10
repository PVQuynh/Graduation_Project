package com.chat.websocket.service;

import com.chat.websocket.dto.request.GroupMemberReq;
import com.chat.websocket.dto.response.GroupMemberRes;
import com.chat.websocket.entity.GroupMember;
import java.util.List;

public interface GroupMemberService {

  List<GroupMemberRes> getGroupMembersByConversationId(long conversationId);

  void save(GroupMemberReq groupMemberReq);

  void saveAll(List<GroupMember> groupMembers);

  void deleteMember(long id);


  //
  //
  //
  GroupMember findByContactIdAndConversationId(long contactId, long conversationID);

  void save(GroupMember groupMember);

}
