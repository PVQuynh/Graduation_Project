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

    ContactRes getByEmail_v2(String email);

    ContactRes getMyContact();

    PageDTO<ContactRes> search(ContactSearchReq contactSearchReq);

    PageDTO<ContactRes> search_v2(int page, int size, String text, boolean ascending, String orderBy);

    void saveContact(ContactReq contactReq);

    void uploadAvatar(UploadAvatarReq uploadAvatarReq);

    //
    //
    //
    Contact findById(long id);

}
