package com.example.user_server.client;

import com.example.user_server.client.request.ContactClientReq;
import com.example.user_server.client.request.UploadAvatarClientReq;
import com.example.user_server.dto.response.MessageResponse;
import jakarta.validation.Valid;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

@FeignClient("chatserver")
@Service
public interface ChatFeignClient {

    @PostMapping(value = "/contacts", consumes = "application/json")
    MessageResponse createContact(@RequestBody @Valid ContactClientReq contactRequest);

    @PostMapping(value = "/contacts/upload-avatar", consumes = "application/json")
    MessageResponse uploadAvatar(@RequestBody UploadAvatarClientReq uploadAvatarReq);

}
