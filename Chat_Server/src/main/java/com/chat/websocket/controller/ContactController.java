package com.chat.websocket.controller;

import com.chat.websocket.dto.PageDTO;
import com.chat.websocket.dto.request.ContactByEmailReq;
import com.chat.websocket.dto.request.ContactReq;
import com.chat.websocket.dto.request.ContactSearchReq;
import com.chat.websocket.dto.request.UploadAvatarReq;
import com.chat.websocket.dto.response.ContactRes;
import com.chat.websocket.dto.response.MessageResponse;
import com.chat.websocket.service.ContactService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

//@Controller
@RestController
@RequestMapping("/contacts")
@RequiredArgsConstructor
public class ContactController {

    private final ContactService contactService;

    @GetMapping("/me")
    public ContactRes getMyContact() {
        try {
            return contactService.getMyContact();
        } catch (Exception ex) {
            return null;
        }
    }

    @GetMapping("/{id}")
    public ContactRes getById(@PathVariable long id) {
        try {
            return contactService.getContactById(id);
        } catch (Exception ex) {
            return null;
        }
    }

    @PostMapping("/find-by-email")
    public ContactRes findByEmail(@RequestBody ContactByEmailReq contactByEmailReq) {
        try {
            return contactService.getByEmail(contactByEmailReq);
        } catch (Exception ex) {
            return null;
        }
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
