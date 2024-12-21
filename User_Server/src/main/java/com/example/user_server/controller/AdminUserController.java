package com.example.user_server.controller;

import com.example.user_server.dto.request.*;
import com.example.user_server.dto.response.MessageResponse;
import com.example.user_server.entity.User;
import com.example.user_server.exception.UnAuthorizedException;
import com.example.user_server.service.KeycloakService;
import com.example.user_server.service.UserService;
import com.example.user_server.utils.PageUtils;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;

@RestController
@RequestMapping("/admin-users")
@RequiredArgsConstructor
public class AdminUserController {
    private final UserService userService;
    private final KeycloakService keycloakService;

    @PostMapping("/create-user")
    public ResponseEntity<MessageResponse> createUser(@RequestBody @Valid RegisterReq registerReq) {
        if (registerReq.getRole().equals("ADMIN")) {
            throw new UnAuthorizedException();
        }
        final String SUCCESS = "Create User Successfully!";
        MessageResponse ms = new MessageResponse();

        //Save Account
        try {
            User user = userService.create(registerReq);
            if (ObjectUtils.isNotEmpty(user)) {
                keycloakService.createUser(registerReq);
            }
            ms.message = SUCCESS;
            return ResponseEntity.ok(ms);

        } catch (Exception e) {
            ms.code = HttpStatus.INTERNAL_SERVER_ERROR.value();
            ms.message = e.getMessage();
            return ResponseEntity
                    .status(ms.code)
                    .body(ms);
        }

    }

    @GetMapping("/all")
    public ResponseEntity<MessageResponse> getAllUser(
            @RequestParam(required = false, defaultValue = "0") int page,
            @RequestParam(required = false, defaultValue = "10") int size,
            @RequestParam(required = false, defaultValue = "name") String orderBy,
            @RequestParam(required = false, defaultValue = "true") boolean ascending

    ) {
        MessageResponse ms = new MessageResponse();
        Pageable pageable = PageUtils.getPageable(page, size, orderBy, ascending);
        ms.data = userService.getAllUser(pageable);
        return ResponseEntity.ok(ms);
    }

    @PutMapping
    public ResponseEntity<MessageResponse> updateUserById(@RequestBody UpdateUserReq updateUserReq) throws ParseException {
        userService.updateUserById(updateUserReq);
        return ResponseEntity.ok(new MessageResponse());
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<MessageResponse> deleteUserById(@PathVariable long userId) {
        userService.deleteUserById(userId);
        return ResponseEntity.ok(new MessageResponse());
    }
}