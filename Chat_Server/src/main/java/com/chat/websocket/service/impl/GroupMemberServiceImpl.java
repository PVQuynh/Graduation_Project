package com.chat.websocket.service.impl;

import com.chat.websocket.dto.request.GroupMemberReq;
import com.chat.websocket.dto.response.GroupMemberRes;
import com.chat.websocket.entity.Contact;
import com.chat.websocket.entity.Conversation;
import com.chat.websocket.entity.GroupMember;
import com.chat.websocket.enum_constant.ConversationType;
import com.chat.websocket.exception.BusinessLogicException;
import com.chat.websocket.repository.GroupMemberRepository;
import com.chat.websocket.service.ContactService;
import com.chat.websocket.service.ConversationService;
import com.chat.websocket.service.GroupMemberService;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GroupMemberServiceImpl implements GroupMemberService {

  private final GroupMemberRepository groupMemberRepository;

  private final ConversationService conversationService;

  private final ContactService contactService;

  @Override
  public List<GroupMemberRes> getGroupMembersByConversationId(long conversationId) {
    List<GroupMember> groupMembers = groupMemberRepository.findGroupMembersByConversationId(conversationId);

    List<GroupMemberRes> groupMemberResList = new ArrayList<>();

    for (GroupMember member : groupMembers) {
      GroupMemberRes groupMemberRes = new GroupMemberRes(member);

      groupMemberResList.add(groupMemberRes);
    }

    return groupMemberResList;
  }

  @Override
  public void save(GroupMemberReq groupMemberReq) {
    Conversation conversation = conversationService.getById(groupMemberReq.getConversationId());
    List<GroupMember> groupMembersAlreadyInConversation = conversation.getGroupMembers();

    // Lấy danh sách contactIds từ groupMembersAlreadyInConversation
    Set<Long> existingContactIds = groupMembersAlreadyInConversation.stream()
            .map(GroupMember::getContact)
            .map(Contact::getId)
            .collect(Collectors.toSet());

    // Loại bỏ những contactId đã trong nhóm
    List<Long> newContactIds = groupMemberReq.getContactIds().stream()
            .filter(contactId -> !existingContactIds.contains(contactId))
            .collect(Collectors.toList());

    // Tao groupMemberList từ các newContactIds còn lại
    List<GroupMember> groupMemberList = newContactIds.stream()
            .map(contactId -> {
              Contact contact = contactService.findById(contactId);

              return GroupMember.builder()
                      .conversation(conversation)
                      .contact(contact)
                      .build();
            })
            .collect(Collectors.toList());

    conversation.setConversationType(ConversationType.GROUP);
    conversation.setGroupMembers(groupMemberList);
    conversationService.save(conversation);
  }


  @Override
  public void saveAll(List<GroupMember> groupMembers) {
    groupMemberRepository.saveAll(groupMembers);
  }

  @Override
  public void deleteMember(long id) {
    groupMemberRepository.deleteById(id);
  }

  @Override
  public GroupMember findByContactIdAndConversationId(long contactId, long conversationID) {
    return groupMemberRepository.findByContactIdAndConversationId(contactId, conversationID).orElseThrow(() -> new BusinessLogicException());
  }

  @Override
  public void save(GroupMember groupMember) {
    groupMemberRepository.save(groupMember);
  }

}
