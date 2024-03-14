package com.example.user_server.controller;

import com.example.user_server.constant.ExceptionConstant;
import com.example.user_server.constant.HTTPCode;
import com.example.user_server.dto.response.MessageResponse;
import com.example.user_server.service.AddFriendService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/friend-ship")
@RequiredArgsConstructor
public class FriendShipControlller {

    private final AddFriendService addFriendService;

    @GetMapping("/sending-list")
    public MessageResponse sendingList() {
        MessageResponse ms = new MessageResponse();
        try {
            ms.data = addFriendService.getSendingList();
        } catch (Exception e) {
            ms.code = HTTPCode.RESOURCE_NOT_FOUND;
            ms.message = ExceptionConstant.RESOURCE_NOT_FOUND;
        }
        return ms;
    }

    @GetMapping("/request-list")
    public MessageResponse requestList() {
        MessageResponse ms = new MessageResponse();
        try {
            ms.data = addFriendService.getRequestList();
        } catch (Exception e) {
            ms.code = HTTPCode.RESOURCE_NOT_FOUND;
            ms.message = ExceptionConstant.RESOURCE_NOT_FOUND;
        }
        return ms;
    }

    @GetMapping("/friend-list")
    public MessageResponse friendList() {
        MessageResponse ms = new MessageResponse();
        try {
            ms.data = addFriendService.getFriendList();
        } catch (Exception e) {
            ms.code = HTTPCode.RESOURCE_NOT_FOUND;
            ms.message = ExceptionConstant.RESOURCE_NOT_FOUND;
        }
        return ms;
    }

    @PostMapping("/add-friend/{userId}")
    public MessageResponse addFriend(@PathVariable long userId) {
        MessageResponse ms = new MessageResponse();
        try {
            if (!addFriendService.addFriend(userId)) {
                ms.code = 409;
                ms.message = "Conflict error!";
            }
        } catch (Exception e) {
            ms.code = HTTPCode.INTERNAL_SERVER_ERROR;
            ms.message = ExceptionConstant.INTERNAL_SERVER_ERROR;
        }
        return ms;
    }

    @PostMapping("/accept-friend/{userId}")
    public MessageResponse acceptFriend(@PathVariable long userId) {
        MessageResponse ms = new MessageResponse();
        try {
            addFriendService.acceptFriend(userId);
        } catch (Exception e) {
            ms.code = HTTPCode.INTERNAL_SERVER_ERROR;
            ms.message = ExceptionConstant.INTERNAL_SERVER_ERROR;
        }
        return ms;
    }

    @DeleteMapping("/cancel-friend/{userId}")
    public MessageResponse cancelFriend(@PathVariable long userId) {
        MessageResponse ms = new MessageResponse();
        try {
            addFriendService.cancelFriend(userId);
        } catch (Exception e) {
            ms.code = HTTPCode.INTERNAL_SERVER_ERROR;
            ms.message = ExceptionConstant.INTERNAL_SERVER_ERROR;
        }
        return ms;
    }


}
