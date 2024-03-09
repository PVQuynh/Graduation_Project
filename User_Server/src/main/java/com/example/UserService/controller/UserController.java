package com.example.UserService.controller;

import com.example.UserService.dto.PageDTO;
import com.example.UserService.dto.UserDTO;
import com.example.UserService.dto.UserDetailDTO;
import com.example.UserService.dto.request.*;
import com.example.UserService.dto.response.MessagesResponse;
import com.example.UserService.service.UserService;
import lombok.RequiredArgsConstructor;
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

    @PutMapping
    public MessagesResponse updateUser(@RequestBody UpdateUserReq updateUserReq) {
        MessagesResponse ms = new MessagesResponse();
        try {
            userService.updateUser(updateUserReq);
        } catch (Exception ex) {
            ms.code = 5000;
            ms.message = ex.getMessage();
        }
        return ms;
    }

    @PostMapping("/changePassword")
    public MessagesResponse changePassword(@RequestBody ChangePasswordReq changePasswordReq) {
        MessagesResponse ms = new MessagesResponse();

        try {
            if (!userService.changePassword(changePasswordReq)) {
                ms.code = 400;
                ms.message = "Change Password failed!";
            }
        } catch (Exception ex) {
            ms.code = 500;
            ms.message = ex.getMessage();
        }

        return ms;
    }

    @PostMapping("/search")
    public PageDTO<UserDTO> GetLists(@RequestBody UserSearchReq userSearchReq) {
        return userService.search(userSearchReq);
    }

    @GetMapping("/{id}")
    public MessagesResponse getById(@PathVariable long id) {
        MessagesResponse ms = new MessagesResponse();

        try {
            ms.data = userService.getUserById(id);
        } catch (Exception ex) {
            ms.code = 500;
            ms.message = ex.getMessage();
        }

        return ms;
    }

    @PostMapping("/uploadAvatar")
    public MessagesResponse getById(@RequestBody UploadAvatarReq uploadAvatarReq) {
        MessagesResponse ms = new MessagesResponse();
        try {
            userService.uploadAvatar(uploadAvatarReq);
        } catch (Exception ex) {
            ms.code = 5000;
            ms.message = ex.getMessage();
        }
        return ms;
    }

}
