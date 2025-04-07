package com.example.user_server.controller;

import com.example.user_server.dto.response.MessageRes;
import com.example.user_server.service.AddFriendService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/friend-ship")
@RequiredArgsConstructor
public class FriendShipController {

    private final AddFriendService addFriendService;

    @GetMapping("/sending-list")
    public ResponseEntity<MessageRes> sendingList() {
        MessageRes ms = new MessageRes();
        ms.data = addFriendService.getSendingList();
        return ResponseEntity.ok(ms);
    }

    @GetMapping("/request-list")
    public ResponseEntity<MessageRes> requestList() {
        MessageRes ms = new MessageRes();
        ms.data = addFriendService.getRequestList();
        return ResponseEntity.ok(ms);
    }

    @GetMapping("/friend-list")
    public ResponseEntity<MessageRes> friendList() {
        MessageRes ms = new MessageRes();
        ms.data = addFriendService.getFriendList();
        return ResponseEntity.ok(ms);
    }

    @PostMapping("/add-friend/{userId}")
    public ResponseEntity<MessageRes> addFriend(@PathVariable long userId) {
        addFriendService.addFriend(userId);
        return ResponseEntity.ok(new MessageRes());
    }

    @PostMapping("/accept-friend/{userId}")
    public ResponseEntity<MessageRes> acceptFriend(@PathVariable long userId) {
        addFriendService.acceptFriend(userId);
        return ResponseEntity.ok(new MessageRes());
    }

    @DeleteMapping("/cancel-friend/{userId}")
    public ResponseEntity<MessageRes> cancelFriend(@PathVariable long userId) {
        addFriendService.cancelFriend(userId);
        return ResponseEntity.ok(new MessageRes());
    }


}
