package com.chat.websocket.service.impl;

import com.chat.websocket.dto.request.GetContactByEmailReq;
import com.chat.websocket.dto.request.UploadAvatarReq;
import com.chat.websocket.dto.response.ContactDetailRes;
import com.chat.websocket.dto.response.ContactRes;
import com.chat.websocket.entity.Contact;
import com.chat.websocket.exception.BusinessLogicException;
import com.chat.websocket.repository.ContactRepository;
import com.chat.websocket.service.ContactService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ContactServiceImpl implements ContactService {

  private final ContactRepository contactRepository;

  @Override
  public void save(Contact contact) {
    contactRepository.save(contact);
  }

  @Override
  public void uploadAvatar(UploadAvatarReq uploadAvatarReq) {
    Contact contact = contactRepository.findByEmail(uploadAvatarReq.email)
        .orElseThrow(() -> new BusinessLogicException());
    contact.setAvatarLocation(uploadAvatarReq.avatarLocation);
    contactRepository.save(contact);

  }

  @Override
  public Contact findById(long id) {
    return contactRepository.findById(id).orElseThrow(BusinessLogicException::new);
  }

  @Override
  public ContactDetailRes getContactByEmail(GetContactByEmailReq getContactByEmailReq) {
    Contact contact = contactRepository.findByEmail(getContactByEmailReq.getEmail())
        .orElseThrow(BusinessLogicException::new);

    return ContactDetailRes.builder()
        .email(contact.getEmail())
        .name(contact.getName())
        .avatarLocation(contact.getAvatarLocation())
        .contactId(contact.getId())
        .build();

  }


}
