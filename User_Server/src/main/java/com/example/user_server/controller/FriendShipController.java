package com.example.user_server.controller;

import com.example.user_server.dto.response.MessageResponse;
import com.example.user_server.service.AddFriendService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/friend-ship")
@RequiredArgsConstructor
public class FriendShipController {

    private final AddFriendService addFriendService;

    @GetMapping("/sending-list")
    public ResponseEntity<MessageResponse> sendingList() {
        MessageResponse ms = new MessageResponse();
        ms.data = addFriendService.getSendingList();
        return ResponseEntity.ok(ms);
    }

    @GetMapping("/request-list")
    public ResponseEntity<MessageResponse> requestList() {
        MessageResponse ms = new MessageResponse();
        ms.data = addFriendService.getRequestList();
        return ResponseEntity.ok(ms);
    }

    @GetMapping("/friend-list")
    public ResponseEntity<MessageResponse> friendList() {
        MessageResponse ms = new MessageResponse();
        ms.data = addFriendService.getFriendList();
        return ResponseEntity.ok(ms);
    }

    @PostMapping("/add-friend/{userId}")
    public ResponseEntity<MessageResponse> addFriend(@PathVariable long userId) {
        addFriendService.addFriend(userId);
        return ResponseEntity.ok(new MessageResponse());
    }

    @PostMapping("/accept-friend/{userId}")
    public ResponseEntity<MessageResponse> acceptFriend(@PathVariable long userId) {
        addFriendService.acceptFriend(userId);
        return ResponseEntity.ok(new MessageResponse());
    }

    @DeleteMapping("/cancel-friend/{userId}")
    public ResponseEntity<MessageResponse> cancelFriend(@PathVariable long userId) {
        addFriendService.cancelFriend(userId);
        return ResponseEntity.ok(new MessageResponse());
    }


}
