package com.chat.websocket.mapper;

import com.chat.websocket.dto.request.MessageRequest;
import com.chat.websocket.entity.Message;
import com.chat.websocket.enum_constant.MessageType;
import com.chat.websocket.exception.BusinessLogicException;
import com.chat.websocket.utils.EmailUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.stereotype.Component;

@Component
public class MessageMapper {
    public Message toEntity(MessageRequest messageRequest) {
        String email = EmailUtils.getCurrentUser();
        if (ObjectUtils.isNotEmpty(email)) {
            throw new BusinessLogicException();
        }
        ModelMapper modelMapper = new ModelMapper();
        TypeMap<MessageRequest, Message> typeMap =  modelMapper.createTypeMap(MessageRequest.class,Message.class);
        Message message =  modelMapper.map(messageRequest,Message.class);
        message.setMessageType(MessageType.valueOf(messageRequest.getMessageType()));
        return  message;
    }
}
