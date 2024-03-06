package com.example.UserService.service;


import com.example.UserService.dto.PageDTO;
import com.example.UserService.dto.UserDTO;
import com.example.UserService.dto.UserDetailDTO;
import com.example.UserService.dto.request.*;

import com.example.UserService.dto.response.MessagesResponse;
import com.example.UserService.entity.User;

import java.text.ParseException;
import java.util.Optional;

public interface UserService {
    User create(RegisterReq registerReq);




    void deleteUser(long id);

    Optional<User> findByEmail(String email);



    User findById(long Id);

    UserDTO getCurrentUser();

    void updateUser(UpdateUserReq updateUserReq) throws ParseException;

    boolean changePassword(ChangePasswordReq changePasswordReq);

    PageDTO<UserDTO> search(UserSearchReq userSearchReq);



    UserDetailDTO getUserById(long userId);

    void uploadAvatar(UploadAvatarReq uploadAvatarReq);
}