package com.chat.chat_server.controller;

import com.chat.chat_server.dto.PageDTO;
import com.chat.chat_server.dto.request.ContactByEmailReq;
import com.chat.chat_server.dto.request.ContactReq;
import com.chat.chat_server.dto.request.ContactSearchReq;
import com.chat.chat_server.dto.request.UploadAvatarReq;
import com.chat.chat_server.dto.response.ContactRes;
import com.chat.chat_server.dto.response.MessageResponse;
import com.chat.chat_server.service.ContactService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

//@Controller
@RestController
@RequestMapping("/contacts")
@RequiredArgsConstructor
public class ContactController {

    private final ContactService contactService;

    @GetMapping("/me")
    public MessageResponse getMyContact() {
        MessageResponse ms = new MessageResponse();

        try {
            ms.data = contactService.getMyContact();
        } catch (Exception ex) {
            ms.code = HttpStatus.NOT_FOUND.value();
            ms.message = HttpStatus.NOT_FOUND.getReasonPhrase();
        }

        return ms;
    }

    @GetMapping("/{id}")
    public MessageResponse getById(@PathVariable long id) {
        MessageResponse ms = new MessageResponse();

        try {
            ms.data = contactService.getContactById(id);
        } catch (Exception ex) {
            ms.code = HttpStatus.NOT_FOUND.value();
            ms.message = HttpStatus.NOT_FOUND.getReasonPhrase();
        }

        return ms;
    }

    @PostMapping("/find-by-email")
    public MessageResponse findByEmail(@RequestBody ContactByEmailReq contactByEmailReq) {
        MessageResponse ms = new MessageResponse();

        try {
            ms.data = contactService.getByEmail(contactByEmailReq);
        } catch (Exception ex) {
            ms.code = HttpStatus.NOT_FOUND.value();
            ms.message = HttpStatus.NOT_FOUND.getReasonPhrase();
        }

        return ms;
    }

    @PostMapping("/search")
    public PageDTO<ContactRes> getList(@RequestBody ContactSearchReq contactSearchReq) {
        return contactService.search(contactSearchReq);
    }

    @PostMapping
    public MessageResponse createContact(@RequestBody ContactReq contactReq) {
        MessageResponse ms = new MessageResponse();

        try {
            contactService.saveContact(contactReq);
        } catch (Exception ex) {
            ms.code = HttpStatus.INTERNAL_SERVER_ERROR.value();
            ms.message = HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase();
        }

        return ms;

    }

    @PostMapping("/upload-avatar")
    public MessageResponse updateAvatar(@RequestBody UploadAvatarReq uploadAvatarReq) {
        MessageResponse ms = new MessageResponse();
        try {
            contactService.uploadAvatar(uploadAvatarReq);
        } catch (Exception ex) {
            ms.code = HttpStatus.INTERNAL_SERVER_ERROR.value();
            ms.message = HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase();
        }
        return ms;

    }


}
