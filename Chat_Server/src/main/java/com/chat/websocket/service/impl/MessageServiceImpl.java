package com.chat.websocket.service.impl;

import com.chat.websocket.constant.MessageStatus;
import com.chat.websocket.dto.response.ChatMessageRes;
import com.chat.websocket.entity.Contact;
import com.chat.websocket.entity.GroupMember;
import com.chat.websocket.entity.Message;
import com.chat.websocket.repository.GroupMemberRepository;
import com.chat.websocket.repository.MessageRepository;
import com.chat.websocket.service.ContactService;
import com.chat.websocket.service.GroupMemberService;
import com.chat.websocket.service.MessageService;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MessageServiceImpl implements MessageService {

  private final MessageRepository messageRepository;
  private final ContactService contactService;

  private final GroupMemberService groupMemberService;

  @Override
  public List<ChatMessageRes> getAllMessageConversation(long conversationId) {
    List<ChatMessageRes> chatMessageResList = new ArrayList<>();
    List<Message> messageList = messageRepository.getMessageByConversationId(conversationId);

    if (ObjectUtils.isNotEmpty(messageList)) {
      chatMessageResList = messageList.stream().map(message -> {
        GroupMember groupMember = message.getGroupMember();
        ChatMessageRes chatMessageRes = new ChatMessageRes();
        if (ObjectUtils.isNotEmpty(groupMember)) {
          Contact contact = contactService.findById(groupMember.getContact().getId());
          chatMessageRes = ChatMessageRes.builder().content(message.getContent())
              .messageType(message.getMessageType().toString())
              .creationTime(message.getCreationTime()).mediaLocation(message.getMediaLocation())
              .status(MessageStatus.SEEN).conversationId(groupMember.getConversation().getId())
              .groupMemberId(groupMember.getId()).name(contact.getName()).build();

          // Update Group Member
          groupMember.setLastActivity(LocalDateTime.now());
          groupMemberService.save(groupMember);


          // Update
          message.setStatus(MessageStatus.SEEN);
          messageRepository.save(message);
        }

        return chatMessageRes;
      }).collect(Collectors.toList());
    }
    return chatMessageResList;
  }

  @Override
  public void deleteMessageConversation(long messageId) {
     messageRepository.deleteById(messageId);
  }
}
