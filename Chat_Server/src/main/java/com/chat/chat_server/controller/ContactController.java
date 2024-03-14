package com.chat.chat_server.controller;

import com.chat.chat_server.constant.ExceptionConstant;
import com.chat.chat_server.constant.HTTPCode;
import com.chat.chat_server.dto.PageDTO;
import com.chat.chat_server.dto.request.ContactByEmailReq;
import com.chat.chat_server.dto.request.ContactReq;
import com.chat.chat_server.dto.request.ContactSearchReq;
import com.chat.chat_server.dto.request.UploadAvatarReq;
import com.chat.chat_server.dto.response.ContactRes;
import com.chat.chat_server.dto.response.MessageResponse;
import com.chat.chat_server.service.ContactService;
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
            ms.code = HTTPCode.INTERNAL_SERVER_ERROR;
            ms.message = ExceptionConstant.INTERNAL_SERVER_ERROR;
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
            ms.code = HTTPCode.INTERNAL_SERVER_ERROR;
            ms.message = ExceptionConstant.INTERNAL_SERVER_ERROR;
        }
        return ms;

    }



}
