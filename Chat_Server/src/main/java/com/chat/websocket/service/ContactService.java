package com.chat.websocket.service;

import com.chat.websocket.dto.request.ContactReq;
import com.chat.websocket.dto.request.UploadAvatarReq;
import com.chat.websocket.dto.response.ContactRes;
import com.chat.websocket.entity.Contact;

public interface ContactService{

    ContactReq getMyContact();

    void saveContact(ContactReq contactReq);

    void uploadAvatar(UploadAvatarReq uploadAvatarReq);

    //
    //
    //
    Contact findById(long id);

    Contact findByEmail(String email);
}
