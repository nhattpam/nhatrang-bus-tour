package com.grouppro.nhatrangbustour.mapper;

import com.grouppro.nhatrangbustour.Entity.User;
import com.grouppro.nhatrangbustour.dto.UserDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);
    UserDTO toDTO(User user);
    User toEntity(UserDTO userDTO);
}
