package com.example.UserService.controller;


import com.example.UserService.dto.response.MessagesResponse;
import com.example.UserService.entity.Role;
import com.example.UserService.service.KeycloakService;
import com.example.UserService.service.RoleService;
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

       try {
           if (!keycloakService.createRole(role)) {
               ms.code = 409;
               ms.message = "Role already exists!";
           } else {
               keycloakService.createRole(role);
           }

       } catch (Exception ex) {
           ms.code = 500;
           ms.message = ex.getMessage();
       }

        return ms;
    }

}
