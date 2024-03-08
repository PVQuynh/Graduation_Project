package com.chat.websocket.controller;

import com.chat.websocket.dto.request.ContactReq;
import com.chat.websocket.dto.request.UploadAvatarReq;
import com.chat.websocket.dto.response.ContactRes;
import com.chat.websocket.dto.response.MessageResponse;
import com.chat.websocket.entity.Contact;
import com.chat.websocket.service.ContactService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

//@Controller
@RestController
@RequestMapping("/contacts")
@RequiredArgsConstructor
public class ContactController {

    private final ContactService contactService;

    @GetMapping("/me")
    public ContactReq getMyContact() {
        try {
            return contactService.getMyContact();
        } catch (Exception ex) {
            return null;
        }
    }

    // Create contact
    @PostMapping
    public MessageResponse createContact(@RequestBody @Valid ContactReq contactReq) {
        MessageResponse ms = new MessageResponse();

        try {
            contactService.saveContact(contactReq);
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



}
