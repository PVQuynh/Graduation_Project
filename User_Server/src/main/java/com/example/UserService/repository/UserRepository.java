package com.example.UserService.repository;


import com.example.UserService.entity.User;
import com.example.UserService.enum_constant.Gender;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);

    @Modifying
    @Transactional
    @Query("update User u set u.name=:name , u.phoneNumber=:phoneNumber, u.address=:address, u.birthDay=:birthDay," +
            "u.gender=:gender where u.email=:email")
    void updateUser(@Param("email") String email, @Param("name") String name, @Param("phoneNumber") String phoneNumber,
                    @Param("address") String address, @Param("birthDay") Date birthDay, @Param("gender") Gender gender);


}
