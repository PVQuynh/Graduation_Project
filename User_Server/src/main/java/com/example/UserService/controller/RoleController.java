package com.example.UserService.controller;


import com.example.UserService.dto.request.RoleReq;
import com.example.UserService.dto.response.MessagesResponse;
import com.example.UserService.entity.Role;
import com.example.UserService.service.KeycloakService;
import com.example.UserService.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/roles")
@RequiredArgsConstructor
public class RoleController {
    private  final KeycloakService keycloakService;
    private  final RoleService roleService;
    @PostMapping
    public MessagesResponse createRole( @RequestBody Role role) {
        MessagesResponse ms = new MessagesResponse();
        keycloakService.createRole(role);
        roleService.create(role);

        return ms;
    }
}
