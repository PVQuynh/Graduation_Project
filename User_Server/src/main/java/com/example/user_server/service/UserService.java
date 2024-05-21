package com.example.user_server.service;


import com.example.user_server.dto.PageDTO;
import com.example.user_server.dto.UserDTO;
import com.example.user_server.dto.UserDetailDTO;
import com.example.user_server.dto.request.*;

import com.example.user_server.entity.User;

import java.text.ParseException;
import java.util.Optional;

public interface UserService {
    User create(RegisterReq registerReq);

    void deleteUser(long id);

    Optional<User> findByEmail(String email);

    User findById(long Id);

    UserDTO getCurrentUser();

    void updateUser(UpdateUserReq updateUserReq) throws ParseException;

    void changePassword(ChangePasswordReq changePasswordReq);

    PageDTO<UserDTO> search(UserSearchReq userSearchReq);

    PageDTO<UserDTO> searchV2(int page, int size, String text, boolean ascending, String orderBy);

    UserDetailDTO getUserById(long userId);

    void uploadAvatar(UploadAvatarReq uploadAvatarReq);
}