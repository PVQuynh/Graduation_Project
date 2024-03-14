package com.chat.chat_server.service;

import com.chat.chat_server.dto.request.GroupMemberReq;
import com.chat.chat_server.dto.response.GroupMemberRes;
import com.chat.chat_server.entity.GroupMember;
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
