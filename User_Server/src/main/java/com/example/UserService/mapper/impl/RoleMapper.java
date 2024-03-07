package com.example.UserService.mapper.impl;

import com.example.UserService.dto.RoleDTO;
import com.example.UserService.entity.Role;
import com.example.UserService.mapper.Mapper;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class RoleMapper implements Mapper<Role, RoleDTO> {

    @Override
    public Role toEntity(RoleDTO dto) {
        ModelMapper modelMapper = new ModelMapper();

        return modelMapper.map(dto, Role.class);
    }

    @Override
    public RoleDTO toDTO(Role entity) {
        ModelMapper modelMapper = new ModelMapper();

        return modelMapper.map(entity, RoleDTO.class);
    }

    @Override
    public List<RoleDTO> toDTOList(List<Role> entityList) {
        return entityList.stream().map(entity->toDTO(entity)).collect(Collectors.toList());

    }

    @Override
    public List<Role> toEntityList(List<RoleDTO> dtoList) {
        return dtoList.stream().map(dto->toEntity(dto)).collect(Collectors.toList());

    }
}
