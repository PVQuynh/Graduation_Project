package com.example.UserService.service;

import com.example.UserService.dto.UserDTO;

import java.util.List;

public interface AddFriendService {

    List<UserDTO> getSendingList();

    List<UserDTO> getRequestList();

    List<UserDTO> getFriendList();

    boolean addFriend(long userId);

    void acceptFriend(long userId);

    void cancelFriend(long userId);
}
