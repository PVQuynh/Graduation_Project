package com.chat.chat_server.service.impl;

import com.chat.chat_server.dto.request.GroupMemberReq;
import com.chat.chat_server.dto.response.GroupMemberRes;
import com.chat.chat_server.entity.Contact;
import com.chat.chat_server.entity.Conversation;
import com.chat.chat_server.entity.GroupMember;
import com.chat.chat_server.enum_constant.ConversationType;
import com.chat.chat_server.exception.BusinessLogicException;
import com.chat.chat_server.repository.GroupMemberRepository;
import com.chat.chat_server.service.ContactService;
import com.chat.chat_server.service.ConversationService;
import com.chat.chat_server.service.GroupMemberService;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.chat.chat_server.utils.EmailUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

@Service
@RequiredArgsConstructor
public class GroupMemberServiceImpl implements GroupMemberService {

  private final GroupMemberRepository groupMemberRepository;

  private final ConversationService conversationService;

  private final ContactService contactService;

  @Override
  public List<GroupMemberRes> getGroupMembersByConversationId(long conversationId) {
    String email = EmailUtils.getCurrentUser();
    if (ObjectUtils.isEmpty(email)) {
      throw new BusinessLogicException();
    }

    List<GroupMember> groupMembers = groupMemberRepository.findGroupMembersByConversationId(conversationId);
    if (groupMembers.isEmpty()) throw new BusinessLogicException();

    List<GroupMemberRes> groupMemberResList = new ArrayList<>();

    for (GroupMember member : groupMembers) {
      GroupMemberRes groupMemberRes = new GroupMemberRes(member);

      groupMemberResList.add(groupMemberRes);
    }

    return groupMemberResList;
  }

  @Override
  public void save(GroupMemberReq groupMemberReq) {
    String email = EmailUtils.getCurrentUser();
    if (ObjectUtils.isEmpty(email)) {
      throw new BusinessLogicException();
    }

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

    if (!newContactIds.isEmpty()) {
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
    }

    conversationService.save(conversation);
  }


  @Override
  public void saveAll(List<GroupMember> groupMembers) {
    String email = EmailUtils.getCurrentUser();
    if (ObjectUtils.isEmpty(email)) {
      throw new BusinessLogicException();
    }

    groupMemberRepository.saveAll(groupMembers);
  }

  @Override
  public void deleteMember(long id) {
    String email = EmailUtils.getCurrentUser();
    if (ObjectUtils.isEmpty(email)) {
      throw new BusinessLogicException();
    }

    groupMemberRepository.deleteById(id);
  }

  @Override
  public GroupMember findByContactIdAndConversationId(long contactId, long conversationID) {
    return groupMemberRepository.findByContactIdAndConversationId(contactId, conversationID).orElseThrow(() -> new BusinessLogicException());
  }

  @Override
  public void save(GroupMember groupMember) {
    String email = EmailUtils.getCurrentUser();
    if (ObjectUtils.isEmpty(email)) {
      throw new BusinessLogicException();
    }

    groupMemberRepository.save(groupMember);
  }

}
