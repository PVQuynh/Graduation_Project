package com.example.hust_learning_server.security;


import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import com.example.hust_learning_server.constant.ExceptionMessage;
import com.example.hust_learning_server.exception.EmailNotFoundException;
import com.example.hust_learning_server.repository.UserRepository;

@Service
public class UserDetailService implements UserDetailsService {
    private final UserRepository userRepository;

    public UserDetailService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return userRepository.findByEmail(email)
                .map(UserDetail::new)
                .orElseThrow(() -> new EmailNotFoundException(ExceptionMessage.EMAIL_NOT_FOUND));

    }
}
