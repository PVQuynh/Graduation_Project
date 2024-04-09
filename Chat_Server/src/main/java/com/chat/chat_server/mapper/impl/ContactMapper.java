package com.chat.chat_server.mapper.impl;

import com.chat.chat_server.dto.response.ContactRes;
import com.chat.chat_server.entity.Contact;
import com.chat.chat_server.mapper.Mapper;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
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
        return dtoList.stream()
                .map(contactRes -> toEntity(contactRes))
                .collect(Collectors.toList());
    }
}
