package com.chat.websocket.service.impl;

import com.chat.websocket.dto.request.ConversationReq;
import com.chat.websocket.dto.response.GrouAttachConvRes;
import com.chat.websocket.dto.response.ConversationAllMeRes;
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
  public Conversation createConversation(ConversationReq conversationReq) {
    Conversation conversation = Conversation.builder()
        .conversationType(ConversationType.valueOf(conversationReq.conversationType))
        .conversationName(conversationReq.conversationName)
        .build();

    List<GroupMember> groupMembers = conversationReq.getMembers().stream()
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
  public List<ConversationAllMeRes> getMyList() {
    String email = EmailUtils.getCurrentUser();
    if (ObjectUtils.isEmpty(email)) {
      throw new BusinessLogicException();
    }

    List<Conversation> conversationList = conversationRepository.getMyConversationList(email);

    List<ConversationAllMeRes> allMe = conversationList.stream()
        .map(conversation -> {
          ConversationAllMeRes conversationAllMeRes = ConversationAllMeRes.builder()
              .conversationId(conversation.getId())
              .build();

          List<GroupMember> groupMemberList = conversation.getGroupMembers();
          List<GrouAttachConvRes> grouAttachConvResList = new ArrayList<>();

          if (ObjectUtils.isNotEmpty(groupMemberList)) {
            grouAttachConvResList = groupMemberList.stream()
                .map(groupMember -> {
                      Contact contact = groupMember.getContact();
                      GrouAttachConvRes grouAttachConvRes = GrouAttachConvRes.builder()
                          .id(contact.getId())
                          .avatarLocation(contact.getAvatarLocation())
                          .name(contact.getName())
                          .email(contact.getEmail())
                          .lastActivity(groupMember.getLastActivity())
                          .build();
                  if (ObjectUtils.isNotEmpty(groupMember.getMessages()) && groupMember.getMessages().size() >0) {
                    grouAttachConvRes.setLastMessage(
                        groupMember.getMessages().get(groupMember.getMessages().size() - 1)
                            .getContent());
                  }
                      return grouAttachConvRes;
                    }

                ).collect(Collectors.toList());
          }
          ;
          conversationAllMeRes.setGrouAttachConvResList(grouAttachConvResList);
          return conversationAllMeRes;
        })
        .collect(Collectors.toList());

    return allMe;

  }

  @Override
  public ConversationAllMeRes getByContactId(long contactId) {
    String email = EmailUtils.getCurrentUser();
    if (ObjectUtils.isEmpty(email)) {
      throw new BusinessLogicException();
    }

    Conversation conversation = conversationRepository.getByContractId(email, contactId);
    ConversationReq conversationReq = null;

    if (ObjectUtils.isEmpty(conversation)) {
      Contact contact = contactRepository.findById(contactId)
          .orElseThrow(BusinessLogicException::new);

      conversationReq = ConversationReq.builder()
          .conversationName(email + "_" + contact.getEmail())
          .conversationType(ConversationType.SINGLE.toString())
          .members(Arrays.asList(email, contact.getEmail()))
          .build();

      conversation = createConversation(conversationReq);

    }
    ConversationAllMeRes conversationAllMeRes = ConversationAllMeRes.builder()
        .conversationId(conversation.getId())
        .build();
    List<GroupMember> groupMemberList = conversation.getGroupMembers();
    List<GrouAttachConvRes> grouAttachConvResList = new ArrayList<>();
    if (ObjectUtils.isNotEmpty(groupMemberList)) {
      grouAttachConvResList = groupMemberList.stream()
          .map(groupMember -> {
                Contact contact = groupMember.getContact();

                GrouAttachConvRes grouAttachConvRes = GrouAttachConvRes.builder()
                    .id(contact.getId())
                    .avatarLocation(contact.getAvatarLocation())
                    .name(contact.getName())
                    .email(contact.getEmail())
                    .lastActivity(groupMember.getLastActivity())
                    .build();
                if (ObjectUtils.isNotEmpty(groupMember.getMessages())) {
                  grouAttachConvRes.setLastMessage(
                      groupMember.getMessages().get(groupMember.getMessages().size() - 1)
                          .getContent());
                }
                return grouAttachConvRes;
              }

          ).collect(Collectors.toList());
      conversationAllMeRes.setGrouAttachConvResList(grouAttachConvResList);
    }

    return conversationAllMeRes;
  }

  @Override
  public Conversation getById(long conversationId) {
    return conversationRepository.findById(conversationId).orElseThrow(BusinessLogicException::new);
  }
}
