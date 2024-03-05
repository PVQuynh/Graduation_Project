package com.example.UserService.service.impl;

import com.example.UserService.dto.request.RoleReq;
import com.example.UserService.entity.Role;
import com.example.UserService.repository.RoleRepository;
import com.example.UserService.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RoleServiceimpl implements RoleService {
    private  final RoleRepository roleRepository;

    @Override
    public void create(Role role) {
        roleRepository.save(role);
    }
}
