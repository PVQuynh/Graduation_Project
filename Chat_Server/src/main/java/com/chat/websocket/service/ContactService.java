package com.chat.websocket.service;

import com.chat.websocket.dto.PageDTO;
import com.chat.websocket.dto.request.ContactByEmailReq;
import com.chat.websocket.dto.request.ContactReq;
import com.chat.websocket.dto.request.ContactSearchReq;
import com.chat.websocket.dto.request.UploadAvatarReq;
import com.chat.websocket.dto.response.ContactRes;
import com.chat.websocket.entity.Contact;

public interface ContactService{

    ContactRes getContactById(long id);

    ContactRes getByEmail(ContactByEmailReq contactByEmailReq);

    ContactRes getMyContact();

    PageDTO<ContactRes> search(ContactSearchReq contactSearchReq);

    void saveContact(ContactReq contactReq);

    void uploadAvatar(UploadAvatarReq uploadAvatarReq);

    //
    //
    //
    Contact findById(long id);

}
