package com.example.user_server.service;

import com.example.user_server.dto.RoleDTO;
import com.example.user_server.entity.Role;

import java.util.List;

public interface RoleService {

    void create(Role role);

    List<RoleDTO> getAllRole();
}
