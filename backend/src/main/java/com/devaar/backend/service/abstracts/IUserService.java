package com.devaar.backend.service.abstracts;

import com.devaar.backend.dao.entity.ProfileEntity;
import com.devaar.backend.model.dto.ProfileDto;
import com.devaar.backend.model.dto.UserDto;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface IUserService {
    List<UserDto> getAllUsers();

    UserDto getUserById(Long id);

    void deleteUserById(Long id);

    UserDto updateUserById(Long id, UserDto userDto);

    UserDto createUser(UserDto userDto);

    List<ProfileDto> getAllProfilesById(Long UserId);

    ProfileDto getProfileById(Long UserId, Long profileId);

    void deleteProfileById(Long UserId, Long profileId);

    void updateProfileById(Long UserId, Long profileId, ProfileDto profileDto);

    byte[] getProfileImageById(Long UserId, Long profileId);

    void updateProfileImageById(Long UserId, Long profileId, MultipartFile profileImage);
}
