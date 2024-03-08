package com.chat.websocket.mapper;

import com.chat.websocket.dto.request.MessageReq;
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
    public Message toEntity(MessageReq messageReq) {
        String email = EmailUtils.getCurrentUser();
        if (ObjectUtils.isNotEmpty(email)) {
            throw new BusinessLogicException();
        }
        ModelMapper modelMapper = new ModelMapper();
        TypeMap<MessageReq, Message> typeMap =  modelMapper.createTypeMap(MessageReq.class,Message.class);
        Message message =  modelMapper.map(messageReq,Message.class);
        message.setMessageType(MessageType.valueOf(messageReq.getMessageType()));
        return  message;
    }
}
