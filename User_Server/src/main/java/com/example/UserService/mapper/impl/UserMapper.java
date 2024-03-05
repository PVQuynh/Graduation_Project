package com.example.UserService.mapper.impl;

import com.example.UserService.dto.UserDTO;
import com.example.UserService.dto.request.RegisterReq;
import com.example.UserService.entity.Role;
import com.example.UserService.entity.User;
import com.example.UserService.enum_constant.Gender;
import com.example.UserService.mapper.Mapper;
import com.example.UserService.repository.RoleRepository;

import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.ObjectUtils;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class UserMapper implements Mapper<User, UserDTO> {

    private final RoleRepository roleRepository;

    @Override
    public User toEntity(UserDTO dto) {

        Gender gender = dto.getGender().equals("MALE") ? Gender.MALE : Gender.FEMALE;
        Role role = roleRepository.findByCode(dto.getRole()).orElse(null);
        ModelMapper modelMapper = new ModelMapper();
        TypeMap<UserDTO, User> typeMap =  modelMapper.createTypeMap(UserDTO.class,User.class);
        typeMap.addMappings(mapping->mapping.map(src->role,User::setRole));

        typeMap.addMappings(mapping->mapping.map(src->gender,User::setGender));
        return modelMapper.map(dto,User.class);
    }

    @Override
    public UserDTO toDTO(User entity) {
        String role = entity.getRole().getCode();
        String gender = ObjectUtils.isNotEmpty(entity.getGender())? entity.getGender().getValue() : null;
        ModelMapper modelMapper = new ModelMapper();
        TypeMap<User, UserDTO> typeMap =  modelMapper.createTypeMap(User.class,UserDTO.class);
        typeMap.addMappings(mapping->mapping.map(src->role,UserDTO::setRole));

        typeMap.addMappings(mapping->mapping.map(src->gender,UserDTO::setGender));
        UserDTO userRes =  modelMapper.map(entity,UserDTO.class);

        return  userRes;
    }

    @Override
    public List<UserDTO> toDTOList(List<User> entityList) {
        return  entityList.stream()
                .map(user -> toDTO(user))
                .collect(Collectors.toList());
    }

    @Override
    public List<User> toEntityList(List<UserDTO> dtoList) {
        return null;
    }
}
