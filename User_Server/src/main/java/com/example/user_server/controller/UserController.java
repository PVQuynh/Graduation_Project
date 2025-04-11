package com.example.user_server.controller;

import com.example.user_server.dto.PageDTO;
import com.example.user_server.dto.UserDTO;
import com.example.user_server.dto.request.*;
import com.example.user_server.dto.response.MessageRes;
import com.example.user_server.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
    public ResponseEntity<MessageRes> getUserInFor_v2() {
        MessageRes ms = new MessageRes();
        ms.data = userService.getCurrentUser();
        return ResponseEntity.ok(ms);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MessageRes> getById(@PathVariable long id) {
        MessageRes ms = new MessageRes();
        ms.data = userService.getUserById(id);
        return ResponseEntity.ok(ms);
    }

    @PostMapping("/search")
    public PageDTO<UserDTO> GetLists(@RequestBody UserSearchReq userSearchReq) {
        return userService.search(userSearchReq);
    }

    @GetMapping("/search/v2")
    public ResponseEntity<MessageRes> getList_v2(
            @RequestParam(required = true) String text,
            @RequestParam(defaultValue = "1", required = true) int page,
            @RequestParam(defaultValue = "10", required = true) int size,
            @RequestParam(required = false) boolean ascending,
            @RequestParam(required = false) String orderBy
    ) {
        MessageRes ms = new MessageRes();
        ms.data = userService.searchV2(page, size, text, ascending, orderBy);
        return ResponseEntity.ok(ms);
    }

    @GetMapping("get-all")
    public ResponseEntity<MessageRes> getAllUsers(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        MessageRes ms = new MessageRes();
        Pageable pageable = PageRequest.of(page, size);
        ms.data = userService.getAllUsers(pageable);
        return ResponseEntity.ok(ms);
    }

    @PutMapping
    public ResponseEntity<MessageRes> updateUser(@RequestBody UpdateUserReq updateUserReq) throws ParseException {
        userService.updateUser(updateUserReq);
        return ResponseEntity.ok(new MessageRes());
    }

    @PostMapping("/change-password")
    public ResponseEntity<MessageRes> changePassword(@RequestBody ChangePasswordReq changePasswordReq) {
        userService.changePassword(changePasswordReq);
        return ResponseEntity.ok(new MessageRes());
    }

    @PostMapping("/upload-avatar")
    public ResponseEntity<MessageRes> getById(@RequestBody UploadAvatarReq uploadAvatarReq) {
        userService.uploadAvatar(uploadAvatarReq);
        return ResponseEntity.ok(new MessageRes());
    }

}