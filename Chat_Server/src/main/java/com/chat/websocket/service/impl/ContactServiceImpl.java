package com.chat.websocket.service.impl;

import com.chat.websocket.dto.request.ContactReq;
import com.chat.websocket.dto.request.UploadAvatarReq;
import com.chat.websocket.dto.response.ContactRes;
import com.chat.websocket.entity.Contact;
import com.chat.websocket.exception.BusinessLogicException;
import com.chat.websocket.repository.ContactRepository;
import com.chat.websocket.service.ContactService;
import com.chat.websocket.utils.EmailUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ContactServiceImpl implements ContactService {

  private final ContactRepository contactRepository;

  @Override
  public ContactReq getMyContact() {
    String email = EmailUtils.getCurrentUser();

    Contact contact = contactRepository.findByEmail(email)
            .orElseThrow(() -> new BusinessLogicException());

      return new ContactReq(contact);

  }

  @Override
  public void saveContact(ContactReq  contactReq) {
    Contact contact = new Contact(contactReq);
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



}
