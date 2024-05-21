package com.example.user_server.controller;


import com.example.user_server.dto.response.MessageResponse;
import com.example.user_server.entity.Role;
import com.example.user_server.service.KeycloakService;
import com.example.user_server.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/roles")
@RequiredArgsConstructor
public class RoleController {
    private final KeycloakService keycloakService;
    private final RoleService roleService;

    @GetMapping("/all")
    public ResponseEntity<MessageResponse> getRoles() {
        MessageResponse ms = new MessageResponse();
        ms.data = roleService.getAllRole();
        return ResponseEntity.ok(ms);
    }

    @PostMapping
    public ResponseEntity<MessageResponse> createRole(@RequestBody Role role) {
        keycloakService.createRole(role);
        roleService.create(role);
        return ResponseEntity.ok(new MessageResponse());
    }

}
