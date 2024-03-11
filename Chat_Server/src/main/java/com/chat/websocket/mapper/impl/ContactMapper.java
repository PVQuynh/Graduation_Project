package com.chat.websocket.mapper.impl;

import com.chat.websocket.dto.response.ContactRes;
import com.chat.websocket.entity.Contact;
import com.chat.websocket.mapper.Mapper;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.ObjectUtils;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class ContactMapper implements Mapper<Contact, ContactRes> {


    @Override
    public Contact toEntity(ContactRes dto) {

        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(dto,Contact.class);
    }

    @Override
    public ContactRes toDTO(Contact entity) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(entity,ContactRes.class);
    }

    @Override
    public List<ContactRes> toDTOList(List<Contact> entityList) {
        return  entityList.stream()
                .map(contact -> toDTO(contact))
                .collect(Collectors.toList());
    }

    @Override
    public List<Contact> toEntityList(List<ContactRes> dtoList) {
        return null;
    }
}
