package com.chat.chat_server.service;

import com.chat.chat_server.dto.PageDTO;
import com.chat.chat_server.dto.request.ContactByEmailReq;
import com.chat.chat_server.dto.request.ContactReq;
import com.chat.chat_server.dto.request.ContactSearchReq;
import com.chat.chat_server.dto.request.UploadAvatarReq;
import com.chat.chat_server.dto.response.ContactRes;
import com.chat.chat_server.entity.Contact;

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
