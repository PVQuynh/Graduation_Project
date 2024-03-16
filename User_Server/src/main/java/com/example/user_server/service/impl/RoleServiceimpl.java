package com.example.user_server.service.impl;

import com.example.user_server.dto.RoleDTO;
import com.example.user_server.entity.Role;
import com.example.user_server.exception.BusinessLogicException;
import com.example.user_server.mapper.impl.RoleMapper;
import com.example.user_server.repository.RoleRepository;
import com.example.user_server.service.RoleService;
import com.example.user_server.utils.EmailUtils;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.ObjectUtils;
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
        if (roles.isEmpty()) throw new BusinessLogicException();

        return roleMapper.toDTOList(roles);
    }
}
