package com.example.user_server.controller;


import com.example.user_server.dto.response.MessageRes;
import com.example.user_server.entity.Role;
import com.example.user_server.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/roles")
@RequiredArgsConstructor
public class RoleController {
    private final RoleService roleService;

    @GetMapping("/all")
    public ResponseEntity<MessageRes> getRoles() {
        MessageRes ms = new MessageRes();
        ms.data = roleService.getAllRole();
        return ResponseEntity.ok(ms);
    }

    @PostMapping
    public ResponseEntity<MessageRes> createRole(@RequestBody Role role) {
        roleService.create(role);
        return ResponseEntity.ok(new MessageRes());
    }

}
