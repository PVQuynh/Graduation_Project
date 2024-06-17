package com.chat.chat_server.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import com.chat.chat_server.entity.User;


public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
}
