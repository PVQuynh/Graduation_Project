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
        ResponseEntity<MessageResponse> res = ResponseEntity.ok(ms);
        try {
            ms.data = addFriendService.getSendingList();
        } catch (Exception e) {
            ms.code = HttpStatus.NOT_FOUND.value();
            ms.message = HttpStatus.NOT_FOUND.getReasonPhrase();
            res = ResponseEntity
                    .status(ms.code)
                    .body(ms);
        }
        return res;
    }


    @GetMapping("/request-list")
    public ResponseEntity<MessageResponse> requestList() {
        MessageResponse ms = new MessageResponse();
        ResponseEntity<MessageResponse> res = ResponseEntity.ok(ms);
        try {
            ms.data = addFriendService.getRequestList();
        } catch (Exception e) {
            ms.code = HttpStatus.NOT_FOUND.value();
            ms.message = HttpStatus.NOT_FOUND.getReasonPhrase();
            res = ResponseEntity
                    .status(ms.code)
                    .body(ms);
        }
        return res;
    }

    @GetMapping("/friend-list")
    public ResponseEntity<MessageResponse> friendList() {
        MessageResponse ms = new MessageResponse();
        ResponseEntity<MessageResponse> res = ResponseEntity.ok(ms);
        try {
            ms.data = addFriendService.getFriendList();
        } catch (Exception e) {
            ms.code = HttpStatus.NOT_FOUND.value();
            ms.message = HttpStatus.NOT_FOUND.getReasonPhrase();
            res = ResponseEntity
                    .status(ms.code)
                    .body(ms);
        }
        return res;
    }

    @PostMapping("/add-friend/{userId}")
    public ResponseEntity<MessageResponse> addFriend(@PathVariable long userId) {
        MessageResponse ms = new MessageResponse();
        ResponseEntity<MessageResponse> res = ResponseEntity.ok(ms);
        try {
            if (!addFriendService.addFriend(userId)) {
                ms.code = 409;
                ms.message = "Conflict error!";
                res = ResponseEntity
                        .status(ms.code)
                        .body(ms);
            }
        } catch (Exception e) {
            ms.code = HttpStatus.INTERNAL_SERVER_ERROR.value();
            ms.message = HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase();
            res = ResponseEntity
                    .status(ms.code)
                    .body(ms);
        }
        return res;
    }

    @PostMapping("/accept-friend/{userId}")
    public ResponseEntity<MessageResponse> acceptFriend(@PathVariable long userId) {
        MessageResponse ms = new MessageResponse();
        ResponseEntity<MessageResponse> res = ResponseEntity.ok(ms);
        try {
            addFriendService.acceptFriend(userId);
        } catch (Exception e) {
            ms.code = HttpStatus.INTERNAL_SERVER_ERROR.value();
            ms.message = HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase();
            res = ResponseEntity
                    .status(ms.code)
                    .body(ms);
        }
        return res;
    }

    @DeleteMapping("/cancel-friend/{userId}")
    public ResponseEntity<MessageResponse> cancelFriend(@PathVariable long userId) {
        MessageResponse ms = new MessageResponse();
        ResponseEntity<MessageResponse> res = ResponseEntity.ok(ms);
        try {
            addFriendService.cancelFriend(userId);
        } catch (Exception e) {
            ms.code = HttpStatus.INTERNAL_SERVER_ERROR.value();
            ms.message = HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase();
            res = ResponseEntity
                    .status(ms.code)
                    .body(ms);
        }
        return res;
    }


}
