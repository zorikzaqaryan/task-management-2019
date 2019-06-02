package com.example.task.mapper;

import com.example.task.model.UserDto;
import com.example.task.repository.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    UserDto userToUserDTO(User entity);

    User userDTOToUser(UserDto dto);
}
