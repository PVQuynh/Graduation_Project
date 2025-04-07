package com.example.user_server.controller;

import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.example.user_server.dto.response.MessageRes;
import com.example.user_server.service.UserService;
import com.example.user_server.utils.PageUtils;
import lombok.RequiredArgsConstructor;
@RestController
@RequestMapping("/authorization")
@RequiredArgsConstructor

public class AuthorizationController {
    private final UserService userService;

    @PostMapping("/{id}")
    public ResponseEntity<MessageRes> approveUser(@PathVariable long id) {
        MessageRes ms = new MessageRes();
        userService.approveUser(id);
        return ResponseEntity.ok(ms);
    }

    @GetMapping("/list-not-approved")
    public ResponseEntity<MessageRes> getUserNotApproved(
        @RequestParam(required = true, defaultValue = "0") int page,
        @RequestParam(required = true, defaultValue = "10") int size
    ) {
        MessageRes ms = new MessageRes();
        Pageable pageable = PageUtils.getPageable(page,size,null,false);
        ms.data  = userService.getUserNotApproved(pageable);
        return ResponseEntity.ok(ms);
    }

}
