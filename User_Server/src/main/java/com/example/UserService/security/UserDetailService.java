package com.example.UserService.security;


import com.example.UserService.constant.ExceptionMessage;
import com.example.UserService.entity.User;
import com.example.UserService.exception.EmailNotFoundException;
import com.example.UserService.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


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
