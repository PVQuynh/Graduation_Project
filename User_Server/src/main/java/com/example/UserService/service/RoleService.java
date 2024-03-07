package com.example.UserService.service;

import com.example.UserService.dto.RoleDTO;
import com.example.UserService.entity.Role;

import java.util.List;

public interface RoleService {

    void create(Role role);

    List<RoleDTO> getAllRole();
}
