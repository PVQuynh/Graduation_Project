package com.example.user_server.controller;


import com.example.user_server.dto.response.MessagesResponse;
import com.example.user_server.entity.Role;
import com.example.user_server.service.KeycloakService;
import com.example.user_server.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/roles")
@RequiredArgsConstructor
public class RoleController {
    private  final KeycloakService keycloakService;
    private  final RoleService roleService;

    @GetMapping("/all")
    public MessagesResponse getRoles(){
        MessagesResponse ms = new MessagesResponse();
        try {
            ms.data = roleService.getAllRole();
        } catch (Exception ex) {
            ms.code = 400;
            ms.message = ex.getMessage();
        }
        return ms;
    }

    @PostMapping
    public MessagesResponse createRole( @RequestBody Role role) {
        MessagesResponse ms = new MessagesResponse();
        keycloakService.createRole(role);
        roleService.create(role);

        return ms;
    }

}
