package com.example.UserService.repository;

import com.example.UserService.entity.FriendShip;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface FriendShipRepository extends JpaRepository<FriendShip, Long> {
    @Query("select fs from FriendShip  fs where fs.sendFriend.id  = :sendUserId and fs.acceptFriend.email = :acceptEmail and fs.status= :status")
    Optional<FriendShip> findBySendFriendEmailAndAcceptUserIdAndStatus(@Param("acceptEmail") String email,
                                                                       @Param("sendUserId") long sentUserId, @Param("status") int status);

    @Query("select fs from FriendShip  fs where fs.sendFriend.id  = :sendUserId and fs.acceptFriend.email = :acceptEmail and fs.status in (100,200)")
    Optional<FriendShip> findCancelFriend(@Param("acceptEmail") String email,
                                          @Param("sendUserId") long sentUserId);

    @Query("select fs from FriendShip  fs where fs.acceptFriend.email  = :acceptEmail  and fs.status =100")
    Optional<List<FriendShip>> findPendingFriend(@Param("acceptEmail") String email);

    @Query("select fs from FriendShip  fs where ( fs.acceptFriend.email  = :email or fs.sendFriend.email = :email)  and fs.status =200")
    Optional<List<FriendShip>> findAllFriend(@Param("email") String email);
    @Query("select case when count(fs)>0 then true else false  end from FriendShip  fs where (( fs.acceptFriend.id  = :currentId and fs.sendFriend.id =:checkId) or (fs.sendFriend.id = :currentId and fs.acceptFriend.id  = :checkId))  and fs.status =200")
    boolean checkFriendShip(@Param("currentId") long currentId, @Param("checkId") long checkId);

    @Query("select fs from FriendShip  fs where (( fs.acceptFriend.id  = :currentId and fs.sendFriend.id =:checkId) or (fs.sendFriend.id = :currentId and fs.acceptFriend.id  = :checkId))  and fs.status =200")
    Optional<FriendShip> checkFriendStatus(@Param("currentId") long currentId, @Param("checkId") long checkId);
}
