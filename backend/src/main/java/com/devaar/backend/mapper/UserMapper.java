package com.devaar.backend.mapper;

import com.devaar.backend.dao.entity.UserEntity;
import com.devaar.backend.model.dto.UserDto;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserDto userEntityToUserDto(UserEntity user);

    UserEntity userDtoToUserEntity(UserDto userDto);

    List<UserEntity> userDtoListToUserEntityList(List<UserDto> userDtoList);

    List<UserDto> userEntityListToUserDtoList(List<UserEntity> userEntityList);
}
