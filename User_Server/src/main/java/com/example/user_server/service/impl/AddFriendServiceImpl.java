package com.example.user_server.service.impl;

import com.example.user_server.constant.FriendShipStatus;
import com.example.user_server.dto.UserDTO;
import com.example.user_server.entity.FriendShip;
import com.example.user_server.entity.User;
import com.example.user_server.exception.ConflictException;
import com.example.user_server.exception.ResourceNotFoundException;
import com.example.user_server.exception.UnAuthorizedException;
import com.example.user_server.mapper.impl.UserMapper;
import com.example.user_server.repository.FriendShipRepository;
import com.example.user_server.repository.UserRepository;
import com.example.user_server.service.AddFriendService;
import com.example.user_server.utils.EmailUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
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
            throw new UnAuthorizedException();
        }
        List<FriendShip> friendShipList = friendShipRepository.findSendingList(email);
        if (ObjectUtils.isEmpty(friendShipList)) return null;;
        List<User> users = friendShipList.stream()
                .map(FriendShip::getAcceptFriend)
                .collect(Collectors.toList());
        return userMapper.toDTOList(users);
    }

    @Override
    public List<UserDTO> getRequestList() {
        String email = EmailUtils.getCurrentUser();
        if (ObjectUtils.isEmpty(email)) {
            throw new UnAuthorizedException();
        }
        List<FriendShip> friendShipList = friendShipRepository.findRequestList(email);
        if (ObjectUtils.isEmpty(friendShipList)) return null;;
        List<User> users = friendShipList.stream()
                .map(FriendShip::getSendFriend)
                .collect(Collectors.toList());
        return userMapper.toDTOList(users);
    }

    @Override
    public List<UserDTO> getFriendList() {
        String email = EmailUtils.getCurrentUser();
        if (ObjectUtils.isEmpty(email)) {
            throw new UnAuthorizedException();
        }
        List<FriendShip> friendShipList = friendShipRepository.findAllFriend(email);
        if (ObjectUtils.isEmpty(friendShipList)) return null;;
        List<User> users = friendShipList.stream()
                .map(friendShip -> {
                    if (friendShip.getAcceptFriend().getEmail().equals(email)) {
                        return friendShip.getSendFriend();
                    } else return friendShip.getAcceptFriend();
                })
                .collect(Collectors.toList());
        return userMapper.toDTOList(users);
    }

    @Transactional
    @Override
    public void addFriend(long userId) {
        String email = EmailUtils.getCurrentUser();
        if (ObjectUtils.isEmpty(email)) {
            throw new UnAuthorizedException();
        }
        User sendUser = userRepository.findByEmail(email).orElseThrow(ResourceNotFoundException::new);
        // Lấy ra danh sách đã gửi lời mời cho mình
        List<FriendShip> friendShipList = friendShipRepository.findSendingListByUserId(userId);
        if (friendShipList.isEmpty()) throw new ResourceNotFoundException();
        List<User> sendingUsers = friendShipList.stream()
                .map(FriendShip::getAcceptFriend)
                .toList();
        if ((sendUser.getId() != userId) && (!sendingUsers.contains(sendUser))) {
            User acceptUser = userRepository.findById(userId).orElseThrow(ResourceNotFoundException::new);
            FriendShip friendShip = FriendShip.builder()
                    .sendFriend(sendUser)
                    .acceptFriend(acceptUser)
                    .status(FriendShipStatus.WAITING_ACCEPT)
                    .build();
            friendShipRepository.save(friendShip);
            return;
        }

        if (sendingUsers.contains(sendUser)) {
            FriendShip friendShip = friendShipRepository.findByAcceptedEmailAndSendingUserIdAndStatus(email, userId, FriendShipStatus.WAITING_ACCEPT).orElseThrow(ResourceNotFoundException::new);
            friendShip.setStatus(FriendShipStatus.FRIEND);
            friendShipRepository.save(friendShip);
            return;
        }
        throw new ConflictException();
    }

    @Transactional
    @Override
    public void acceptFriend(long userId) {
        String email = EmailUtils.getCurrentUser();
        if (ObjectUtils.isEmpty(email)) {
            throw new UnAuthorizedException();
        }
        FriendShip friendShip = friendShipRepository.findByAcceptedEmailAndSendingUserIdAndStatus(email, userId, FriendShipStatus.WAITING_ACCEPT)
                .orElseThrow(ResourceNotFoundException::new);
        friendShip.setStatus(FriendShipStatus.FRIEND);
        friendShipRepository.save(friendShip);
    }

    @Transactional
    @Override
    public void cancelFriend(long userId) {
        String email = EmailUtils.getCurrentUser();
        if (ObjectUtils.isEmpty(email)) {
            throw new UnAuthorizedException();
        }

        FriendShip friendShip = friendShipRepository.findFriendShipByEmailAndId(email, userId).orElseThrow(ResourceNotFoundException::new);
        friendShipRepository.delete(friendShip);
    }

}
