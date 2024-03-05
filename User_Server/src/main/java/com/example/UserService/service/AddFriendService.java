package com.example.UserService.service;

import com.example.UserService.dto.UserDTO;

import java.util.List;

public interface AddFriendService {
     void addFriend(long userId);

    void accept(long userId);

    void cancel(long userId);

    List<UserDTO> getPending();

    List<UserDTO> getFriend();
}
