package com.example.user_server.controller;

import com.example.user_server.dto.request.AuthenticationReq;
import com.example.user_server.dto.request.RefreshTokenRq;
import com.example.user_server.dto.response.AuthenticationRes;
import com.example.user_server.entity.TokenObj;
import com.example.user_server.exception.RefreshTokenFailedException;
import com.example.user_server.service.AuthenticationService;
import com.example.user_server.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthenticationController {
    private final RedisTemplate<String, Object> redisTemplate;

    private final AuthenticationService authenticationService;


    private final UserService userService;

    @PostMapping("/login")
    public AuthenticationRes Login(
            @RequestBody @Valid AuthenticationReq authenticationReq) {
        // check is approved
        userService.checkApproved(authenticationReq.getEmail());
        // check user be not deleted
        userService.checkDeleted(authenticationReq.getEmail());

        AuthenticationRes authenticationRes = authenticationService.authenticate(authenticationReq);
        TokenObj tokenObj = new TokenObj();
        tokenObj.setEmail(authenticationReq.getEmail());
        tokenObj.setPassword(authenticationReq.getPassword());
        tokenObj.setCreated(LocalDateTime.now());
        redisTemplate.opsForHash().put(authenticationRes.getAccessToken(), authenticationRes.getRefreshToken().hashCode(), tokenObj);
        redisTemplate.expire(authenticationRes.getRefreshToken(), 300, TimeUnit.SECONDS);
        return new AuthenticationRes(authenticationRes.getAccessToken(), authenticationRes.getRefreshToken());
    }

    @PostMapping("/refresh-token")
    public AuthenticationRes refresh(HttpServletRequest request,
                                     HttpServletResponse response,
                                     @RequestBody @Valid RefreshTokenRq refreshTokenRq) throws IOException {
        String refreshToken = refreshTokenRq.getRefresh_token();
        TokenObj tokenObj = (TokenObj) redisTemplate.opsForHash().get(refreshToken, refreshToken.hashCode());

        if (ObjectUtils.isEmpty(tokenObj)) {
            throw new RefreshTokenFailedException("Refresh Token isn't match or expired!");
        }
        String accessToken = authenticationService.refreshToken(request, response);
        return new AuthenticationRes(accessToken, refreshTokenRq.getRefresh_token());


    }
}
