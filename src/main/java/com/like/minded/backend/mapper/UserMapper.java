package com.like.minded.backend.mapper;

import com.like.minded.backend.domain.user.User;
import com.like.minded.backend.dto.user.UserRegistrationDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    @Mapping(target = "userId", ignore = true)
    @Mapping(target = "username", source = "username")
    @Mapping(target = "password", source = "password")
    @Mapping(target = "email", source = "email")
    @Mapping(target = "userRole", ignore = true)
    User mapToUser(UserRegistrationDto userRegistrationDto);
}
