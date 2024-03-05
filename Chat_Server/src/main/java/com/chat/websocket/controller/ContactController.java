package com.chat.websocket.controller;

import com.chat.websocket.dto.request.ContactRequest;
import com.chat.websocket.dto.request.GetContactByEmailReq;
import com.chat.websocket.dto.request.UploadAvatarReq;
import com.chat.websocket.dto.response.ContactDetailRes;
import com.chat.websocket.dto.response.ContactRes;
import com.chat.websocket.dto.response.MessageResponse;
import com.chat.websocket.entity.Contact;
import com.chat.websocket.service.ContactService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//@Controller
@RestController
@RequestMapping("/contacts")
@RequiredArgsConstructor
public class ContactController {

    private final ContactService contactService;


    // Create contact
    @PostMapping
    public MessageResponse createContact(@RequestBody @Valid ContactRequest contactRequest) {
        MessageResponse ms = new MessageResponse();

        try {
            Contact contact = new Contact(contactRequest);
            contactService.save(contact);
        } catch (Exception ex) {
            ms.code = 500;
            ms.message = ex.getMessage();
        }


        return ms;

    }

    @PostMapping("/upload-avatar")
    public MessageResponse updateAvatar(@RequestBody UploadAvatarReq uploadAvatarReq) {
        MessageResponse ms = new MessageResponse();
        try {
            contactService.uploadAvatar(uploadAvatarReq);
        }
        catch (Exception ex) {
            ms.code = 500;
            ms.message = ex.getMessage();
        }
        return ms;

    }

    @PostMapping("getByEmail")
    public ContactDetailRes getContactByEmail(@RequestBody GetContactByEmailReq getContactByEmailReq) {
        return contactService.getContactByEmail(getContactByEmailReq);
    }


}
