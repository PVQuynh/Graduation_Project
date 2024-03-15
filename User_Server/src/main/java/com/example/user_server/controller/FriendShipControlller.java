package com.example.user_server.controller;

import com.example.user_server.dto.response.MessageResponse;
import com.example.user_server.service.AddFriendService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
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
            ms.code = HttpStatus.NOT_FOUND.value();
            ms.message = HttpStatus.NOT_FOUND.getReasonPhrase();
        }
        return ms;
    }

    @GetMapping("/request-list")
    public MessageResponse requestList() {
        MessageResponse ms = new MessageResponse();
        try {
            ms.data = addFriendService.getRequestList();
        } catch (Exception e) {
            ms.code = HttpStatus.NOT_FOUND.value();
            ms.message = HttpStatus.NOT_FOUND.getReasonPhrase();
        }
        return ms;
    }

    @GetMapping("/friend-list")
    public MessageResponse friendList() {
        MessageResponse ms = new MessageResponse();
        try {
            ms.data = addFriendService.getFriendList();
        } catch (Exception e) {
            ms.code = HttpStatus.NOT_FOUND.value();
            ms.message = HttpStatus.NOT_FOUND.getReasonPhrase();
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
            ms.code = HttpStatus.INTERNAL_SERVER_ERROR.value();
            ms.message = HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase();
        }
        return ms;
    }

    @PostMapping("/accept-friend/{userId}")
    public MessageResponse acceptFriend(@PathVariable long userId) {
        MessageResponse ms = new MessageResponse();
        try {
            addFriendService.acceptFriend(userId);
        } catch (Exception e) {
            ms.code = HttpStatus.INTERNAL_SERVER_ERROR.value();
            ms.message = HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase();
        }
        return ms;
    }

    @DeleteMapping("/cancel-friend/{userId}")
    public MessageResponse cancelFriend(@PathVariable long userId) {
        MessageResponse ms = new MessageResponse();
        try {
            addFriendService.cancelFriend(userId);
        } catch (Exception e) {
            ms.code = HttpStatus.INTERNAL_SERVER_ERROR.value();
            ms.message = HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase();
        }
        return ms;
    }


}
