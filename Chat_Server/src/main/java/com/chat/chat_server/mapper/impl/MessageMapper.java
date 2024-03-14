package com.chat.chat_server.mapper.impl;

import com.chat.chat_server.dto.response.MessageRes;
import com.chat.chat_server.entity.Message;
import com.chat.chat_server.mapper.Mapper;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class MessageMapper implements Mapper<Message, MessageRes> {


    @Override
    public Message toEntity(MessageRes dto) {

        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(dto,Message.class);
    }

    @Override
    public MessageRes toDTO(Message entity) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(entity,MessageRes.class);
    }

    @Override
    public List<MessageRes> toDTOList(List<Message> entityList) {
        return  entityList.stream()
                .map(message -> toDTO(message))
                .collect(Collectors.toList());
    }

    @Override
    public List<Message> toEntityList(List<MessageRes> dtoList) {
        return dtoList.stream().map(dto->toEntity(dto)).collect(Collectors.toList());
    }
}
