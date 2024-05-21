package com.example.user_server.controller;

import com.example.user_server.dto.PageDTO;
import com.example.user_server.dto.UserDTO;
import com.example.user_server.dto.request.*;
import com.example.user_server.dto.response.MessageResponse;
import com.example.user_server.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("/me")
    public ResponseEntity<UserDTO> getUserInFor() {
        return ResponseEntity.ok(userService.getCurrentUser());
    }

    @GetMapping("/me/v2")
    public ResponseEntity<MessageResponse> getUserInFor_v2() {
        MessageResponse ms = new MessageResponse();
        ms.data = userService.getCurrentUser();
        return ResponseEntity.ok(ms);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MessageResponse> getById(@PathVariable long id) {
        MessageResponse ms = new MessageResponse();
        ms.data = userService.getUserById(id);
        return ResponseEntity.ok(ms);
    }

    @PostMapping("/search")
    public PageDTO<UserDTO> GetLists(@RequestBody UserSearchReq userSearchReq) {
        return userService.search(userSearchReq);
    }

    @GetMapping("/search/v2")
    public ResponseEntity<MessageResponse> getList_v2(
            @RequestParam(required = true) String text,
            @RequestParam(defaultValue = "1", required = true) int page,
            @RequestParam(defaultValue = "10", required = true) int size,
            @RequestParam(required = false) boolean ascending,
            @RequestParam(required = false) String orderBy
    ) {
        MessageResponse ms = new MessageResponse();
        ms.data = userService.searchV2(page, size, text, ascending, orderBy);
        return ResponseEntity.ok(ms);
    }

    @PutMapping
    public ResponseEntity<MessageResponse> updateUser(@RequestBody UpdateUserReq updateUserReq) throws ParseException {
        userService.updateUser(updateUserReq);
        return ResponseEntity.ok(new MessageResponse());
    }

    @PostMapping("/change-password")
    public ResponseEntity<MessageResponse> changePassword(@RequestBody ChangePasswordReq changePasswordReq) {
        userService.changePassword(changePasswordReq);
        return ResponseEntity.ok(new MessageResponse());
    }

    @PostMapping("/upload-avatar")
    public ResponseEntity<MessageResponse> getById(@RequestBody UploadAvatarReq uploadAvatarReq) {
        userService.uploadAvatar(uploadAvatarReq);
        return ResponseEntity.ok(new MessageResponse());
    }

}