package com.example.user_server.controller;

import com.example.user_server.dto.PageDTO;
import com.example.user_server.dto.UserDTO;
import com.example.user_server.dto.request.*;
import com.example.user_server.dto.response.MessageResponse;
import com.example.user_server.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("/me")
    public UserDTO getUserInFor() {
        try {
            return userService.getCurrentUser();
        } catch (Exception ex) {
            return null;
        }
    }

    @GetMapping("/{id}")
    public MessageResponse getById(@PathVariable long id) {
        MessageResponse ms = new MessageResponse();

        try {
            ms.data = userService.getUserById(id);
        } catch (Exception ex) {
            ms.code = HttpStatus.NOT_FOUND.value();
            ms.message = HttpStatus.NOT_FOUND.getReasonPhrase();
        }

        return ms;
    }

    @PostMapping("/search")
    public PageDTO<UserDTO> GetLists(@RequestBody UserSearchReq userSearchReq) {
        return userService.search(userSearchReq);
    }

    @PutMapping
    public MessageResponse updateUser(@RequestBody UpdateUserReq updateUserReq) {
        MessageResponse ms = new MessageResponse();
        try {
            userService.updateUser(updateUserReq);
        } catch (Exception ex) {
            ms.code = HttpStatus.INTERNAL_SERVER_ERROR.value();
            ms.message = HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase();
        }
        return ms;
    }

    @PostMapping("/change-password")
    public MessageResponse changePassword(@RequestBody ChangePasswordReq changePasswordReq) {
        MessageResponse ms = new MessageResponse();

        try {
            if (!userService.changePassword(changePasswordReq)) {
                ms.code = HttpStatus.NOT_FOUND.value();
                ms.message = HttpStatus.NOT_FOUND.getReasonPhrase();
            }
        } catch (Exception ex) {
            ms.code = HttpStatus.INTERNAL_SERVER_ERROR.value();
            ms.message = HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase();
        }

        return ms;
    }

    @PostMapping("/upload-avatar")
    public MessageResponse getById(@RequestBody UploadAvatarReq uploadAvatarReq) {
        MessageResponse ms = new MessageResponse();
        try {
            userService.uploadAvatar(uploadAvatarReq);
        } catch (Exception ex) {
            ms.code = HttpStatus.INTERNAL_SERVER_ERROR.value();
            ms.message = HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase();
        }
        return ms;
    }

}