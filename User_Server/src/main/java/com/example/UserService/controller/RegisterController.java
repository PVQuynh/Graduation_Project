package com.example.UserService.controller;


import com.example.UserService.client.ChatFeignClient;
import com.example.UserService.dto.request.ConfirmOTP;
import com.example.UserService.dto.request.RegisterReq;
import com.example.UserService.dto.response.MessagesResponse;
import com.example.UserService.entity.User;
import com.example.UserService.service.EmailService;
import com.example.UserService.service.KeycloakService;
import com.example.UserService.service.OTPService;
import com.example.UserService.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/register")
@RequiredArgsConstructor
public class RegisterController {

    private final   KeycloakService keycloakService;

    private final UserService userService;

    private final OTPService otpService;

    private final EmailService emailService;

    private final RedisTemplate<String, Object> redisTemplate;

    private  final ChatFeignClient client;



    @PostMapping("/generateOtp")
    public MessagesResponse generateOTP(@RequestBody @Valid RegisterReq registerReq) {
        MessagesResponse ms = new MessagesResponse();
        ms.message = "Sent";
        String email = registerReq.getEmail();

        try {
            if (userService.findByEmail(registerReq.getEmail()).isPresent()) {
                ms.code = 404;
                ms.message = "Your email has been used!";
            } else {
                int otp = otpService.generateOTP(email);
                //Generate The Template to send OTP

                String message = "Your OTP verified number is: " + otp;
                redisTemplate.opsForHash().getOperations().delete(email);
                redisTemplate.opsForHash().put(email,email.hashCode(), registerReq);
                redisTemplate.expire(email, 300, TimeUnit.SECONDS);

                emailService.sendOtpMessage(registerReq.getEmail(), "OTP verified code", message);
            }

        } catch (Exception ex) {
            ms.code = HttpStatus.NOT_ACCEPTABLE.value();
            ms.message = ex.getMessage();
        }
        return ms;
    }

    @PostMapping("/validateOtp")
    public MessagesResponse validateOtp(@RequestBody @Valid ConfirmOTP confirmOTP) {
        final String SUCCESS = "Register Successfully!";
        final String FAIL = "Entered Otp is NOT valid. Please Retry!";

        MessagesResponse ms = new MessagesResponse();
        ms.message = SUCCESS;
        String email = confirmOTP.getEmail();
        RegisterReq registerReq =   (RegisterReq) redisTemplate.opsForHash().get(email,email.hashCode());
        int otpnum = confirmOTP.getOtpNum();

        //Validate the Otp
        if (otpnum >= 0) {
            int serverOtp = otpService.getOtp(email);
            if (serverOtp > 0) {
                if (otpnum == serverOtp) {
                    otpService.clearOTP(email);
                    //Save Account
                    try {
                        User user = userService.create(registerReq);
                        if (ObjectUtils.isNotEmpty(user)) {
                            keycloakService.createUser(registerReq);
                        }
                        redisTemplate.opsForHash().getOperations().delete(email);
                    } catch (Exception e) {
                        ms.code = HttpStatus.INTERNAL_SERVER_ERROR.value();
                        ms.message = e.getMessage();
                    }
                } else {
                    ms.code = HttpStatus.UNAUTHORIZED.value();
                    ms.message = FAIL;
                }
            } else {
                ms.code = HttpStatus.UNAUTHORIZED.value();
                ms.message = FAIL;
            }
        } else {
            ms.code = HttpStatus.UNAUTHORIZED.value();
            ms.message = FAIL;
        }
        return ms;
    }

    ;


}
