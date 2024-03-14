package com.example.user_server.controller;

import com.example.user_server.dto.response.MessagesResponse;
import com.example.user_server.service.AddFriendService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/friend-ship")
@RequiredArgsConstructor
public class FriendShipControlller {

    private final AddFriendService addFriendService;

    @GetMapping("/sending-list")
    public MessagesResponse sendingList() {
        MessagesResponse ms = new MessagesResponse();
        try {
            ms.data = addFriendService.getSendingList();
        } catch (Exception e) {
            ms.code = 500;
            ms.message = e.getMessage();
        }
        return ms;
    }

    @GetMapping("/request-list")
    public MessagesResponse requestList() {
        MessagesResponse ms = new MessagesResponse();
        try {
            ms.data = addFriendService.getRequestList();
        } catch (Exception e) {
            ms.code = 500;
            ms.message = e.getMessage();
        }
        return ms;
    }

    @GetMapping("/friend-list")
    public MessagesResponse friendList() {
        MessagesResponse ms = new MessagesResponse();
        try {
            ms.data = addFriendService.getFriendList();
        } catch (Exception e) {
            ms.code = 500;
            ms.message = e.getMessage();
        }
        return ms;
    }

    @PostMapping("/add-friend/{userId}")
    public MessagesResponse addFriend(@PathVariable long userId) {
        MessagesResponse ms = new MessagesResponse();
        try {
            if (!addFriendService.addFriend(userId)) {
                ms.code = 409;
                ms.message = "Conflict error!";
            }
        } catch (Exception e) {
            ms.code = 500;
            ms.message = e.getMessage();
        }
        return ms;
    }

    @PostMapping("/accept-friend/{userId}")
    public MessagesResponse acceptFriend(@PathVariable long userId) {
        MessagesResponse ms = new MessagesResponse();
        try {
            addFriendService.acceptFriend(userId);
        } catch (Exception e) {
            ms.code = 500;
            ms.message = e.getMessage();
        }
        return ms;
    }

    @DeleteMapping("/cancel-friend/{userId}")
    public MessagesResponse cancelFriend(@PathVariable long userId) {
        MessagesResponse ms = new MessagesResponse();
        try {
            addFriendService.cancelFriend(userId);
        } catch (Exception e) {
            ms.code = 500;
            ms.message = e.getMessage();
        }
        return ms;
    }


}
