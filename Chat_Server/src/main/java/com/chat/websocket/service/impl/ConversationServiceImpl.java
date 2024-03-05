package com.chat.websocket.service.impl;

import com.chat.websocket.dto.request.CreateConversationReq;
import com.chat.websocket.dto.response.ContactRes;
import com.chat.websocket.dto.response.GetListConversationRes;
import com.chat.websocket.entity.Contact;
import com.chat.websocket.entity.Conversation;
import com.chat.websocket.entity.GroupMember;
import com.chat.websocket.enum_constant.ConversationType;
import com.chat.websocket.exception.BusinessLogicException;
import com.chat.websocket.repository.ContactRepository;
import com.chat.websocket.repository.ConversationRepository;
import com.chat.websocket.service.ConversationService;
import com.chat.websocket.utils.EmailUtils;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class ConversationServiceImpl implements ConversationService {

  private final ContactRepository contactRepository;

  private final ConversationRepository conversationRepository;

  @Override
  public Conversation createConversation(CreateConversationReq createConversationReq) {
    Conversation conversation = Conversation.builder()
        .conversationType(ConversationType.valueOf(createConversationReq.conversationType))
        .conversationName(createConversationReq.conversationName)
        .build();
    List<GroupMember> groupMembers = createConversationReq.getMembers().stream()
        .map(email -> {
          Contact contact = contactRepository.findByEmail(email)
              .orElseThrow(() -> new BusinessLogicException("User : " + email + " doesn't exist"));
          return contact;
        })
        .map(contact -> GroupMember.builder()
            .contact(contact)
            .conversation(conversation)
            .joinTime(LocalDateTime.now())
            .build()
        )
        .collect(Collectors.toList());
    conversation.setGroupMembers(groupMembers);
    return conversationRepository.save(conversation);

  }

  @Override
  public void deleteById(long conversationID) {
    conversationRepository.deleteById(conversationID);
  }

  @Override
  public List<GetListConversationRes> getMyList() {
    String email = EmailUtils.getCurrentUser();
    if (ObjectUtils.isEmpty(email)) {
      throw new BusinessLogicException();
    }
    List<Conversation> conversationList = conversationRepository.getMyConversationList(email);
    List<GetListConversationRes> getListConversationResList = conversationList.stream()
        .map(conversation -> {
          GetListConversationRes getListConversationRes = GetListConversationRes.builder()
              .conversationId(conversation.getId())
              .build();
          List<GroupMember> groupMemberList = conversation.getGroupMembers();
          List<ContactRes> contactResList = new ArrayList<>();
          if (ObjectUtils.isNotEmpty(groupMemberList)) {
            contactResList = groupMemberList.stream()
                .map(groupMember -> {
                      Contact contact = groupMember.getContact();
                      ContactRes contactRes = ContactRes.builder()
                          .id(contact.getId())
                          .avatarLocation(contact.getAvatarLocation())
                          .name(contact.getName())
                          .email(contact.getEmail())
                          .lastActivity(groupMember.getLastActivity())
                          .build();
                  if (ObjectUtils.isNotEmpty(groupMember.getMessages()) && groupMember.getMessages().size() >0) {
                    contactRes.setLastMessage(
                        groupMember.getMessages().get(groupMember.getMessages().size() - 1)
                            .getContent());
                  }
                      return contactRes;
                    }

                ).collect(Collectors.toList());
          }
          ;
          getListConversationRes.setContactResList(contactResList);
          return getListConversationRes;
        })
        .collect(Collectors.toList());

    return getListConversationResList;

  }

  @Override
  public GetListConversationRes getByContactId(long contactId) {
    String email = EmailUtils.getCurrentUser();
    if (ObjectUtils.isEmpty(email)) {
      throw new BusinessLogicException();
    }
    Conversation conversation = conversationRepository.getByContractId(email, contactId);
    CreateConversationReq createConversationReq = null;
    if (ObjectUtils.isEmpty(conversation)) {
      Contact contact = contactRepository.findById(contactId)
          .orElseThrow(BusinessLogicException::new);
      createConversationReq = CreateConversationReq.builder()
          .conversationName(email + "_" + contact.getEmail())
          .conversationType(ConversationType.SINGLE.toString())
          .members(Arrays.asList(email, contact.getEmail()))
          .build();

      conversation = createConversation(createConversationReq);

    }
    GetListConversationRes getListConversationRes = GetListConversationRes.builder()
        .conversationId(conversation.getId())
        .build();
    List<GroupMember> groupMemberList = conversation.getGroupMembers();
    List<ContactRes> contactResList = new ArrayList<>();
    if (ObjectUtils.isNotEmpty(groupMemberList)) {
      contactResList = groupMemberList.stream()
          .map(groupMember -> {
                Contact contact = groupMember.getContact();

                ContactRes contactRes = ContactRes.builder()
                    .id(contact.getId())
                    .avatarLocation(contact.getAvatarLocation())
                    .name(contact.getName())
                    .email(contact.getEmail())
                    .lastActivity(groupMember.getLastActivity())
                    .build();
                if (ObjectUtils.isNotEmpty(groupMember.getMessages())) {
                  contactRes.setLastMessage(
                      groupMember.getMessages().get(groupMember.getMessages().size() - 1)
                          .getContent());
                }
                return contactRes;
              }

          ).collect(Collectors.toList());
      getListConversationRes.setContactResList(contactResList);
    }

    return getListConversationRes;
  }

  @Override
  public Conversation getById(long conversationId) {
    return conversationRepository.findById(conversationId).orElseThrow(BusinessLogicException::new);
  }
}
