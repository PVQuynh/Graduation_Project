package com.example.user_server.client;

import com.example.user_server.client.request.ContacClientReq;
import com.example.user_server.client.request.UploadAvatarClientReq;
import com.example.user_server.dto.response.MessagesResponse;
import jakarta.validation.Valid;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

@FeignClient("chatserver")
@Service
public interface ChatFeignClient {

    @PostMapping(value = "/contacts", consumes = "application/json")
    MessagesResponse createContact(@RequestBody @Valid ContacClientReq contactRequest);

    @PostMapping(value = "/contacts/upload-avatar", consumes = "application/json")
    MessagesResponse uploadAvatar(@RequestBody UploadAvatarClientReq uploadAvatarReq);

}
