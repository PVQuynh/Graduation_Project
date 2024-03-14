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
        String email = EmailUtils.getCurrentUser();
        if (ObjectUtils.isEmpty(email)) {
            throw new BusinessLogicException();
        }

        roleRepository.save(role);
    }

    @Override
    public List<RoleDTO> getAllRole() {
        String email = EmailUtils.getCurrentUser();
        if (ObjectUtils.isEmpty(email)) {
            throw new BusinessLogicException();
        }
        
        List<Role> roles = roleRepository.findAll();
        return roleMapper.toDTOList(roles);
    }
}
