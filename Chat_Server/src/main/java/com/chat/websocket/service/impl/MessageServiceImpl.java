package com.chat.websocket.service.impl;

import com.chat.websocket.constant.MessageStatus;
import com.chat.websocket.dto.request.MessageReq;
import com.chat.websocket.dto.request.UpdateMessageReq;
import com.chat.websocket.dto.response.MessageRes;
import com.chat.websocket.entity.Contact;
import com.chat.websocket.entity.GroupMember;
import com.chat.websocket.entity.Message;
import com.chat.websocket.exception.BusinessLogicException;
import com.chat.websocket.repository.GroupMemberRepository;
import com.chat.websocket.repository.MessageRepository;
import com.chat.websocket.service.ContactService;
import com.chat.websocket.service.GroupMemberService;
import com.chat.websocket.service.MessageService;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.chat.websocket.utils.EmailUtils;
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
    public List<MessageRes> getAllMessageConversation(long conversationId) {
        List<MessageRes> messageResList = new ArrayList<>();
        List<Message> messageList = messageRepository.getMessageByConversationId(conversationId);

        if (ObjectUtils.isNotEmpty(messageList)) {
            messageResList = messageList.stream().map(message -> {
                GroupMember groupMember = message.getGroupMember();
                MessageRes messageRes = new MessageRes();

                if (ObjectUtils.isNotEmpty(groupMember)) {
                    Contact contact = contactService.findById(groupMember.getContact().getId());

                    messageRes = MessageRes.builder()
                            .messageId(message.getId())
                            .content(message.getContent())
                            .messageType(message.getMessageType())
                            .mediaLocation(message.getMediaLocation())
                            .status(message.getStatus())
                            .created(message.getCreated())
                            .conversationId(groupMember.getConversation().getId())
                            .contactId(contact.getId())
                            .build();

                    // Update Group Member
                    groupMember.setLastActivity(LocalDateTime.now());
                    groupMemberService.save(groupMember);

                    // Update
                    message.setStatus(MessageStatus.SEEN);
                    messageRepository.save(message);
                }

                return messageRes;
            }).collect(Collectors.toList());
        }
        return messageResList;
    }

    @Override
    public void saveMessage(long conversationId, MessageReq messageReq) {
        GroupMember groupMember = groupMemberService.findByContactIdAndConversationId(messageReq.getContactId(), conversationId);
        if (ObjectUtils.isNotEmpty(groupMember)) {
            Message message = Message.builder()
                    .content(messageReq.getContent())
                    .messageType(messageReq.getMessageType())
                    .mediaLocation(messageReq.getMediaLocation())
                    .status(MessageStatus.SENT)
                    .groupMember(groupMember)
                    .build();

            messageRepository.save(message);
        }
    }

    @Override
    public void setSeenForMessage(long conversationId) {
        String email = EmailUtils.getCurrentUser();
        if (ObjectUtils.isEmpty(email)) {
            throw new BusinessLogicException();
        }

        messageRepository.setSeenForMessage(conversationId, email);

    }

    @Override
    public void setSeenForMessageByContactId(long conversationId, long contactId) {

        messageRepository.setSeenForMessageByContactId(conversationId, contactId);

    }

    @Override
    public void updateMessage(UpdateMessageReq updateMessageReq) {
        String email = EmailUtils.getCurrentUser();
        if (ObjectUtils.isEmpty(email)) {
            throw new BusinessLogicException();
        }

        Message message = messageRepository.findById(updateMessageReq.getMessageId()).orElseThrow(BusinessLogicException::new);
        message.setContent(updateMessageReq.getContent());

        messageRepository.save(message);
    }

    @Override
    public void deleteMessage(long messageId) {
        messageRepository.deleteById(messageId);
    }
}
