package com.example.user_server.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FriendShipId implements Serializable {

    private User sendFriend;

    private User acceptFriend;

}
