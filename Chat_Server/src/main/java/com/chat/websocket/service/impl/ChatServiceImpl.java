package com.chat.websocket.service.impl;

import com.chat.websocket.constant.MessageStatus;
import com.chat.websocket.dto.request.MessageReq;
import com.chat.websocket.entity.GroupMember;
import com.chat.websocket.entity.Message;
import com.chat.websocket.enum_constant.MessageType;
import com.chat.websocket.exception.BusinessLogicException;
import com.chat.websocket.repository.GroupMemberRepository;
import com.chat.websocket.service.ChatService;
import com.chat.websocket.utils.EmailUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@RequiredArgsConstructor
@Slf4j
@Service
public class ChatServiceImpl implements ChatService {

    private  final GroupMemberRepository groupMemberRepository;
    @Override
    public void sendMessage(MessageReq messageReq, int conversationID) {
        Message message = Message.builder()
                .content(messageReq.getContent())
                .messageType(MessageType.valueOf(messageReq.getMessageType()))
                .mediaLocation(messageReq.getMediaLocation())
                .status(MessageStatus.SENT)
                .build();

        String email = EmailUtils.getCurrentUser();

        if (ObjectUtils.isNotEmpty(email)) {
            GroupMember groupMember = groupMemberRepository.findByEmailAndConversationId(email,conversationID).orElseThrow(()-> new BusinessLogicException());
            List<Message> messageList  = groupMember.getMessages();
            messageList.add(message);
            groupMember.setLastActivity(LocalDateTime.now());
            groupMemberRepository.save(groupMember);
        }
    }
}
