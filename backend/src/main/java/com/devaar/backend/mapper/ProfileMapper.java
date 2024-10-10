package com.devaar.backend.mapper;

import com.devaar.backend.dao.entity.ProfileEntity;
import com.devaar.backend.dao.entity.UserEntity;
import com.devaar.backend.model.dto.ProfileDto;
import com.devaar.backend.model.dto.UserDto;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProfileMapper {

    ProfileDto profileEntityToProfileDto(ProfileEntity profileEntity);

    ProfileEntity profileDtoToProfileEntity(ProfileDto profileDto);

    List<ProfileEntity> profileDtoListToProfileEntityList(List<ProfileDto> profileDtoList);

    List<ProfileDto> profileEntityListToProfileDtoList(List<ProfileEntity> profileEntityList);
}
