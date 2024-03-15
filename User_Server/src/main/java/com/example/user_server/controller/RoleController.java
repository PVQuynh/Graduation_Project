package com.example.user_server.controller;


import com.example.user_server.dto.response.MessageResponse;
import com.example.user_server.entity.Role;
import com.example.user_server.service.KeycloakService;
import com.example.user_server.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/roles")
@RequiredArgsConstructor
public class RoleController {
    private  final KeycloakService keycloakService;
    private  final RoleService roleService;

    @GetMapping("/all")
    public MessageResponse getRoles(){
        MessageResponse ms = new MessageResponse();
        try {
            ms.data = roleService.getAllRole();
        } catch (Exception ex) {
            ms.code = HttpStatus.NOT_FOUND.value();
            ms.message = HttpStatus.NOT_FOUND.getReasonPhrase();
        }
        return ms;
    }

    @PostMapping
    public MessageResponse createRole(@RequestBody Role role) {
        MessageResponse ms = new MessageResponse();
        try {
            keycloakService.createRole(role);
            roleService.create(role);
        } catch (Exception ex) {
            ms.code = HttpStatus.INTERNAL_SERVER_ERROR.value();
            ms.message = HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase();
        }
        return ms;
    }

}
