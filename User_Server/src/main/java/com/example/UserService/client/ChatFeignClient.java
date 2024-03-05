package com.example.UserService.client;

import com.example.UserService.client.request.ContactRequest;
import com.example.UserService.client.request.UploadAvatarClientReq;
import com.example.UserService.dto.response.MessagesResponse;
import jakarta.validation.Valid;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient("chatserver")
@Service
public interface ChatFeignClient {
    @PostMapping(value = "/contacts",consumes = "application/json")
     MessagesResponse createContact(@RequestBody @Valid ContactRequest contactRequest);

    @PostMapping(value = "/contacts/upload-avatar",consumes = "application/json")
    MessagesResponse uploadAvatar(@RequestBody UploadAvatarClientReq uploadAvatarReq);
}
