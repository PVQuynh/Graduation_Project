package com.example.user_server.service;

import com.example.user_server.dto.UserDTO;

import java.util.List;

public interface AddFriendService {

    List<UserDTO> getSendingList();

    List<UserDTO> getRequestList();

    List<UserDTO> getFriendList();

    void addFriend(long userId);

    void acceptFriend(long userId);

    void cancelFriend(long userId);
}
