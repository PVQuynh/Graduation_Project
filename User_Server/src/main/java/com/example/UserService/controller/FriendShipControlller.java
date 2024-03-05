package com.example.UserService.controller;

import com.example.UserService.dto.response.MessagesResponse;
import com.example.UserService.service.AddFriendService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/friend-ship")
@RequiredArgsConstructor
public class FriendShipControlller {
    private  final AddFriendService addFriendService;
    @GetMapping("/add-friend/{userId}")
    public MessagesResponse addFriend(@PathVariable long userId) {
        MessagesResponse ms = new MessagesResponse();
        try {
            addFriendService.addFriend(userId);
        }
        catch (Exception e) {
            ms.code=500;
            ms.message=e.getMessage();
        }
        return  ms;
    }
    @GetMapping("/accept-friend/{userId}")
    public MessagesResponse acceptFriend(@PathVariable long userId) {
        MessagesResponse ms = new MessagesResponse();
        try {
            addFriendService.accept(userId);
        }
        catch (Exception e) {
            ms.code=500;
            ms.message=e.getMessage();
        }
        return  ms;
    }
    @GetMapping("/cancel-friend/{userId}")
    public MessagesResponse cancelFriend(@PathVariable long userId) {
        MessagesResponse ms = new MessagesResponse();
        try {
            addFriendService.cancel(userId);
        }
        catch (Exception e) {
            ms.code=500;
            ms.message=e.getMessage();
        }
        return  ms;
    }
    @GetMapping("/pending")
    public MessagesResponse getPending() {
        MessagesResponse ms = new MessagesResponse();
        try {
           ms.data= addFriendService.getPending();
        }
        catch (Exception e) {
            ms.code=500;
            ms.message=e.getMessage();
        }
        return  ms;
    }
    @GetMapping("/friend")
    public MessagesResponse getFriend() {
        MessagesResponse ms = new MessagesResponse();
        try {
            ms.data= addFriendService.getFriend();
        }
        catch (Exception e) {
            ms.code=500;
            ms.message=e.getMessage();
        }
        return  ms;
    }
}
