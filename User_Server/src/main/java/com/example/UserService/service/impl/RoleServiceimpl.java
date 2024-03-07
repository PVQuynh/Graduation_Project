package com.example.UserService.service.impl;

import com.example.UserService.dto.RoleDTO;
import com.example.UserService.entity.Role;
import com.example.UserService.mapper.impl.RoleMapper;
import com.example.UserService.repository.RoleRepository;
import com.example.UserService.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RoleServiceimpl implements RoleService {
    private  final RoleRepository roleRepository;
    private final RoleMapper roleMapper;

    @Override
    public void create(Role role) {
        roleRepository.save(role);
    }

    @Override
    public List<RoleDTO> getAllRole() {
        List<Role> roles = roleRepository.findAll();
        return roleMapper.toDTOList(roles);
    }
}
