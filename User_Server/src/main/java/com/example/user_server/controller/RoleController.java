package com.example.user_server.controller;


import com.example.user_server.constant.ExceptionConstant;
import com.example.user_server.constant.HTTPCode;
import com.example.user_server.dto.response.MessageResponse;
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
    public MessageResponse getRoles(){
        MessageResponse ms = new MessageResponse();
        try {
            ms.data = roleService.getAllRole();
        } catch (Exception ex) {
            ms.code = HTTPCode.RESOURCE_NOT_FOUND;
            ms.message = ExceptionConstant.RESOURCE_NOT_FOUND;
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
            ms.code = HTTPCode.INTERNAL_SERVER_ERROR;
            ms.message = ExceptionConstant.INTERNAL_SERVER_ERROR;
        }
        return ms;
    }

}
