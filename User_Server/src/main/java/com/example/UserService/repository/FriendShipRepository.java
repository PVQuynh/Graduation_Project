package com.example.UserService.repository;

import com.example.UserService.entity.FriendShip;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface FriendShipRepository extends JpaRepository<FriendShip, Long> {
    @Query("select fs from FriendShip fs where (fs.sendFriend.email=:email and fs.acceptFriend.id=:id) or (fs.sendFriend.id=:id and fs.acceptFriend.email=:email)")
    Optional<FriendShip> findFriendShipByEmailAndId(@Param("email") String email, @Param("id") long id);

    @Query("select fs from FriendShip  fs where fs.acceptFriend.email=:acceptedFriend and fs.sendFriend.id=:sendingUserId and fs.status=:status")
    Optional<FriendShip> findByAcceptedEmailAndSendingUserIdAndStatus(@Param("acceptedFriend") String acceptedFriend, @Param("sendingUserId") long sendingUserId, @Param("status") int status);

    @Query("select fs from FriendShip fs where fs.acceptFriend.email=:acceptEmail and fs.status =100")
    Optional<List<FriendShip>> findRequestList(@Param("acceptEmail") String acceptEmail);

    @Query("select fs from FriendShip  fs where fs.sendFriend.email=:sendFriend  and fs.status =100")
    Optional<List<FriendShip>> findSendingList(@Param("sendFriend") String sendFriend);

    @Query("select fs from FriendShip  fs where fs.sendFriend.id=:id")
    Optional<List<FriendShip>> findSendingListByUserId(@Param("id") long id);

    @Query("select fs from FriendShip  fs where ( fs.acceptFriend.email=:email or fs.sendFriend.email=:email)  and fs.status =200")
    Optional<List<FriendShip>> findAllFriend(@Param("email") String email);

    @Query("select case when count(fs)>0 then true else false  end from FriendShip  fs where (( fs.acceptFriend.id  = :currentId and fs.sendFriend.id =:checkId) or (fs.sendFriend.id = :currentId and fs.acceptFriend.id  = :checkId))  and fs.status =200")
    boolean checkFriendShip(@Param("currentId") long currentId, @Param("checkId") long checkId);

    @Query("select fs from FriendShip  fs where (( fs.acceptFriend.id=:currentId and fs.sendFriend.id=:checkId) or (fs.sendFriend.id=:currentId and fs.acceptFriend.id  = :checkId))  and fs.status =200")
    Optional<FriendShip> checkFriendStatus(@Param("currentId") long currentId, @Param("checkId") long checkId);
}
