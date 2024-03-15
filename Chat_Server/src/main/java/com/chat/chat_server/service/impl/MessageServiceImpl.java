package com.chat.chat_server.service.impl;

import com.chat.chat_server.constant.MessageStatus;
import com.chat.chat_server.dto.request.MessageLimitReq;
import com.chat.chat_server.dto.request.MessageReq;
import com.chat.chat_server.dto.request.UpdateMessageReq;
import com.chat.chat_server.dto.response.MessageRes;
import com.chat.chat_server.entity.Contact;
import com.chat.chat_server.entity.GroupMember;
import com.chat.chat_server.entity.Message;
import com.chat.chat_server.exception.BusinessLogicException;
import com.chat.chat_server.mapper.impl.MessageMapper;
import com.chat.chat_server.repository.MessageRepository;
import com.chat.chat_server.service.ContactService;
import com.chat.chat_server.service.GroupMemberService;
import com.chat.chat_server.service.MessageService;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.chat.chat_server.utils.EmailUtils;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MessageServiceImpl implements MessageService {

    private final MessageRepository messageRepository;

    private final MessageMapper messageMapper;

    private final ContactService contactService;

    private final GroupMemberService groupMemberService;

    @Override
    public List<MessageRes> getAllMessageConversation(long conversationId) {
        String email = EmailUtils.getCurrentUser();
        if (org.springframework.util.ObjectUtils.isEmpty(email)) {
            throw new BusinessLogicException();
        }

        List<MessageRes> messageResList = new ArrayList<>();
        List<Message> messageList = messageRepository.getMessageByConversationId(conversationId);
        if (messageList.isEmpty()) throw new BusinessLogicException();


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
    public List<MessageRes> messageLimits(MessageLimitReq messageLimitReq) {
        String email = EmailUtils.getCurrentUser();
        if (org.springframework.util.ObjectUtils.isEmpty(email)) {
            throw new BusinessLogicException();
        }

        Pageable pageable = PageRequest.of(messageLimitReq.getPage()-1, messageLimitReq.getSize());

        List<Message> messages = messageRepository.findMessageLimitsByConversationId(messageLimitReq.getConversationId(), pageable);
        if (messages.isEmpty()) throw new BusinessLogicException();

        return messageMapper.toDTOList(messages);
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
        String email = EmailUtils.getCurrentUser();
        if (ObjectUtils.isEmpty(email)) {
            throw new BusinessLogicException();
        }

        messageRepository.deleteById(messageId);
    }
}
