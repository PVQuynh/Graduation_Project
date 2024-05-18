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

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("/me")
    public ResponseEntity<UserDTO> getUserInFor() {
        try {
            return ResponseEntity.ok(userService.getCurrentUser());
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @GetMapping("/me/v2")
    public ResponseEntity<MessageResponse> getUserInFor_v2() {
        MessageResponse ms = new MessageResponse();
        ResponseEntity<MessageResponse> res = ResponseEntity.ok(ms);
        try {
            ms.data = userService.getCurrentUser();
        } catch (Exception ex) {
            ms.code = HttpStatus.NOT_FOUND.value();
            ms.message = HttpStatus.NOT_FOUND.getReasonPhrase(); 
            res = ResponseEntity
                    .status(ms.code)
                    .body(ms);
        }

        return res;
    }

    @GetMapping("/{id}")
    public ResponseEntity<MessageResponse> getById(@PathVariable long id) {
        MessageResponse ms = new MessageResponse();
        ResponseEntity<MessageResponse> res = ResponseEntity.ok(ms);
        try {
            ms.data = userService.getUserById(id);
        } catch (Exception ex) {
            ms.code = HttpStatus.NOT_FOUND.value();
            ms.message = HttpStatus.NOT_FOUND.getReasonPhrase();
            res = ResponseEntity
                    .status(ms.code)
                    .body(ms);
        }

        return res;
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
        ResponseEntity<MessageResponse> res = ResponseEntity.ok(ms);
        try {
            ms.data = userService.search_v2(page, size, text, ascending, orderBy);
        } catch (Exception ex) {
            ms.code = HttpStatus.NOT_FOUND.value();
            ms.message = HttpStatus.NOT_FOUND.getReasonPhrase();
            res = ResponseEntity
                    .status(ms.code)
                    .body(ms);
        }
        return res;
    }

    @PutMapping
    public ResponseEntity<MessageResponse> updateUser(@RequestBody UpdateUserReq updateUserReq) {
        MessageResponse ms = new MessageResponse();
        ResponseEntity<MessageResponse> res = ResponseEntity.ok(ms);
        try {
            userService.updateUser(updateUserReq);
        } catch (Exception ex) {
            ms.code = HttpStatus.INTERNAL_SERVER_ERROR.value();
            ms.message = HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase();
            res = ResponseEntity
                    .status(ms.code)
                    .body(ms);
        }
        return res;
    }

    @PostMapping("/change-password")
    public ResponseEntity<MessageResponse> changePassword(@RequestBody ChangePasswordReq changePasswordReq) {
        MessageResponse ms = new MessageResponse();
        ResponseEntity<MessageResponse> res = ResponseEntity.ok(ms);
        try {
            if (!userService.changePassword(changePasswordReq)) {
                ms.code = HttpStatus.NOT_FOUND.value();
                ms.message = HttpStatus.NOT_FOUND.getReasonPhrase();
                res = ResponseEntity
                        .status(ms.code)
                        .body(ms);
            }
        } catch (Exception ex) {
            ms.code = HttpStatus.INTERNAL_SERVER_ERROR.value();
            ms.message = HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase();
            res = ResponseEntity
                    .status(ms.code)
                    .body(ms);
        }
        return res;
    }

    @PostMapping("/upload-avatar")
    public ResponseEntity<MessageResponse> getById(@RequestBody UploadAvatarReq uploadAvatarReq) {
        MessageResponse ms = new MessageResponse();
        ResponseEntity<MessageResponse> res = ResponseEntity.ok(ms);
        try {
            userService.uploadAvatar(uploadAvatarReq);
        } catch (Exception ex) {
            ms.code = HttpStatus.INTERNAL_SERVER_ERROR.value();
            ms.message = HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase();
            res = ResponseEntity
                    .status(ms.code)
                    .body(ms);
        }
        return res;
    }

}