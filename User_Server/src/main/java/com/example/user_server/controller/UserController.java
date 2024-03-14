package com.example.user_server.controller;

import com.example.user_server.dto.PageDTO;
import com.example.user_server.dto.UserDTO;
import com.example.user_server.dto.request.*;
import com.example.user_server.dto.response.MessagesResponse;
import com.example.user_server.service.UserService;
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

    @PostMapping("/search")
    public PageDTO<UserDTO> GetLists(@RequestBody UserSearchReq userSearchReq) {
        return userService.search(userSearchReq);
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

    @PostMapping("/change-password")
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

    @PostMapping("/upload-avatar")
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
