package com.example.UserService.controller;

import com.example.UserService.dto.PageDTO;
import com.example.UserService.dto.UserDTO;
import com.example.UserService.dto.UserDetailDTO;
import com.example.UserService.dto.request.ChangePasswordReq;
import com.example.UserService.dto.request.UpdateUserReq;
import com.example.UserService.dto.request.UploadAvatarReq;
import com.example.UserService.dto.request.UserSearchReq;
import com.example.UserService.dto.response.MessagesResponse;
import com.example.UserService.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("/getUserInfor")
    public UserDTO getUserInFor() {
        return userService.getCurrentUser();
    }
    @PutMapping
    public MessagesResponse updateUser(@RequestBody UpdateUserReq updateUserReq){
        MessagesResponse ms = new MessagesResponse();
        try {
            userService.updateUser(updateUserReq);
        }
        catch (Exception exception) {
            ms.code=5000;
            ms.message= "Internal Server Error!";
        }
        return ms;
    }
    @PostMapping("/changePassword")
    public MessagesResponse changePassword(@RequestBody ChangePasswordReq changePasswordReq) {
        MessagesResponse ms = new MessagesResponse();
        try {
             userService.changePassword(changePasswordReq);
        }
        catch (Exception ex) {
            ms.code=500;
            ms.message="Change Password failed!";
        }
        return  ms;
    }
    @PostMapping("/api/search")
    public PageDTO<UserDTO> GetLists(@RequestBody UserSearchReq userSearchReq){
        return  userService.search(userSearchReq);
    }

    @GetMapping("/{userId}")
    public UserDetailDTO getById(@PathVariable long userId) {
        return  userService.getUserById(userId);
    }
    @PostMapping("/uploadAvatar")
    public MessagesResponse getById(@RequestBody UploadAvatarReq uploadAvatarReq) {
        MessagesResponse ms = new MessagesResponse();
        try {
            userService.uploadAvatar(uploadAvatarReq);
        }
        catch (Exception ex) {
          ms.code = 5000;
          ms.message = ex.getMessage();
        }
return  ms;
    }

}
