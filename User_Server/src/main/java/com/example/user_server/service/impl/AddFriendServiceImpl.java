package com.example.user_server.service.impl;

import com.example.user_server.constant.FriendShipStatus;
import com.example.user_server.dto.UserDTO;
import com.example.user_server.entity.FriendShip;
import com.example.user_server.entity.User;
import com.example.user_server.exception.BusinessLogicException;
import com.example.user_server.mapper.impl.UserMapper;
import com.example.user_server.repository.FriendShipRepository;
import com.example.user_server.repository.UserRepository;
import com.example.user_server.service.AddFriendService;
import com.example.user_server.utils.EmailUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AddFriendServiceImpl implements AddFriendService {
    private final FriendShipRepository friendShipRepository;
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Override
    public List<UserDTO> getSendingList() {
        String email = EmailUtils.getCurrentUser();
        if (ObjectUtils.isEmpty(email)) {
            throw new BusinessLogicException();
        }

        List<FriendShip> friendShipList = friendShipRepository.findSendingList(email)
                .orElseThrow(() -> new BusinessLogicException());
        if (friendShipList.isEmpty()) throw new BusinessLogicException();

        List<User> users = friendShipList.stream()
                .map(friendShip -> friendShip.getAcceptFriend())
                .collect(Collectors.toList());
        return userMapper.toDTOList(users);
    }

    @Override
    public List<UserDTO> getRequestList() {
        String email = EmailUtils.getCurrentUser();
        if (ObjectUtils.isEmpty(email)) {
            throw new BusinessLogicException();
        }

        List<FriendShip> friendShipList = friendShipRepository.findRequestList(email)
                .orElseThrow(() -> new BusinessLogicException());
        if (friendShipList.isEmpty()) throw new BusinessLogicException();

        List<User> users = friendShipList.stream()
                .map(friendShip -> friendShip.getSendFriend())

                .collect(Collectors.toList());
        return userMapper.toDTOList(users);
    }

    @Override
    public List<UserDTO> getFriendList() {
        String email = EmailUtils.getCurrentUser();
        if (ObjectUtils.isEmpty(email)) {
            throw new BusinessLogicException();
        }

        List<FriendShip> friendShipList = friendShipRepository.findAllFriend(email)
                .orElseThrow(() -> new BusinessLogicException());
        if (friendShipList.isEmpty()) throw new BusinessLogicException();

        List<User> users = friendShipList.stream()
                .map(friendShip -> {
                    if (friendShip.getAcceptFriend().getEmail().equals(email)) {
                        return friendShip.getSendFriend();
                    } else return friendShip.getAcceptFriend();
                })

                .collect(Collectors.toList());

        return userMapper.toDTOList(users);
    }

    @Override
    public boolean addFriend(long userId) {
        String email = EmailUtils.getCurrentUser();
        if (ObjectUtils.isEmpty(email)) {
            throw new BusinessLogicException();
        }

        User sendUser = userRepository.findByEmail(email).orElseThrow(() -> new BusinessLogicException());

        // Lấy ra danh sách đã gửi lời mời cho mình
        List<FriendShip> friendShipList = friendShipRepository.findSendingListByUserId(userId)
                .orElseThrow(() -> new BusinessLogicException());
        List<User> sendingUsers = friendShipList.stream()
                .map(friendShip -> friendShip.getAcceptFriend())
                .collect(Collectors.toList());

        if ((sendUser.getId() != userId) && (!sendingUsers.contains(sendUser))) {
            User acceptUser = userRepository.findById(userId).orElseThrow(() -> new BusinessLogicException());

            FriendShip friendShip = FriendShip.builder()
                    .sendFriend(sendUser)
                    .acceptFriend(acceptUser)
                    .status(FriendShipStatus.WAITING_ACCEPT)
                    .build();

            friendShipRepository.save(friendShip);

            return true;
        }

        if (sendingUsers.contains(sendUser)) {
            FriendShip friendShip = friendShipRepository.findByAcceptedEmailAndSendingUserIdAndStatus(email, userId, FriendShipStatus.WAITING_ACCEPT).orElseThrow(() -> new BusinessLogicException());

            if (ObjectUtils.isEmpty(friendShip)) {
                throw new BusinessLogicException();
            }

            friendShip.setStatus(FriendShipStatus.FRIEND);
            friendShipRepository.save(friendShip);

            return true;
        }

        return false;
    }

    @Override
    public void acceptFriend(long userId) {
        String email = EmailUtils.getCurrentUser();
        if (ObjectUtils.isEmpty(email)) {
            throw new BusinessLogicException();
        }

        FriendShip friendShip = friendShipRepository.findByAcceptedEmailAndSendingUserIdAndStatus(email, userId, FriendShipStatus.WAITING_ACCEPT)
                .orElseThrow(() -> new BusinessLogicException());
        if (ObjectUtils.isEmpty(friendShip)) {
            throw new BusinessLogicException();
        }

        friendShip.setStatus(FriendShipStatus.FRIEND);
        friendShipRepository.save(friendShip);
    }

    @Override
    public void cancelFriend(long userId) {
        String email = EmailUtils.getCurrentUser();
        if (ObjectUtils.isEmpty(email)) {
            throw new BusinessLogicException();
        }

        FriendShip friendShip = friendShipRepository.findFriendShipByEmailAndId(email, userId).orElseThrow(() -> new BusinessLogicException());
        friendShipRepository.delete(friendShip);
    }

}
