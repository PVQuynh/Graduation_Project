package com.chat.websocket.service.impl;

import com.chat.websocket.dto.request.GroupMemberReq;
import com.chat.websocket.dto.response.GroupMemberRes;
import com.chat.websocket.entity.Contact;
import com.chat.websocket.entity.Conversation;
import com.chat.websocket.entity.GroupMember;
import com.chat.websocket.exception.BusinessLogicException;
import com.chat.websocket.repository.GroupMemberRepository;
import com.chat.websocket.service.ContactService;
import com.chat.websocket.service.ConversationService;
import com.chat.websocket.service.GroupMemberService;
import java.time.LocalDateTime;
import java.util.List;
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
  public GroupMemberRes getGroupMemberByConversationId(long conversationId) {
    GroupMember groupMember = groupMemberRepository.findGroupMemberByConversationId(conversationId).orElseThrow(BusinessLogicException::new);

      return new GroupMemberRes(groupMember);
  }

  @Override
  public void save(GroupMemberReq groupMemberReq) {
    Conversation conversation = conversationService.getById(groupMemberReq.getConversationId());

    List<GroupMember> groupMemberList = groupMemberReq.getContactIds().stream()
        .map(contactId -> {
          Contact contact = contactService.findById(contactId);

          return GroupMember.builder()
              .conversation(conversation)
              .contact(contact)
              .build();
        })
        .collect(Collectors.toList());

    groupMemberRepository.saveAll(groupMemberList);
  }

  @Override
  public void saveAll(List<GroupMember> groupMembers) {
    groupMemberRepository.saveAll(groupMembers);
  }

  @Override
  public void deleteMember(long groupMemberId) {
    GroupMember groupMember = groupMemberRepository.findById(groupMemberId).orElseThrow(
        BusinessLogicException::new);
    groupMember.setLastActivity(LocalDateTime.now());
    groupMember.setActive(false);
    groupMemberRepository.save(groupMember);
  }

  @Override
  public void save(GroupMember groupMember) {
    groupMemberRepository.save(groupMember);
  }

}
