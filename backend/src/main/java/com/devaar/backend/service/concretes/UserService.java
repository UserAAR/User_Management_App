package com.devaar.backend.service.concretes;

import com.devaar.backend.Exception.EntityNotFoundException;
import com.devaar.backend.dao.entity.ProfileEntity;
import com.devaar.backend.dao.entity.UserEntity;
import com.devaar.backend.dao.repository.UserRepo;
import com.devaar.backend.model.dto.ProfileDto;
import com.devaar.backend.model.dto.UserDto;
import com.devaar.backend.service.abstracts.IUserService;
import com.devaar.backend.mapper.ProfileMapper;
import com.devaar.backend.mapper.UserMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class UserService implements IUserService {

    private final UserRepo userRepo;
    private final UserMapper userMapper;
    private final ProfileMapper profileMapper;

    public List<UserDto> getAllUsers() {
        List<UserEntity> userEntities = userRepo.findAll();
        return userMapper.userEntityListToUserDtoList(userEntities);
    }

    @Override
    public UserDto getUserById(Long id) {
        UserEntity userEntity = userRepo.findById(id).orElseThrow(() -> new EntityNotFoundException("User Entity not found,id:" + id));
        return userMapper.userEntityToUserDto(userEntity);
    }

    @Override
    public void deleteUserById(Long id) {
        userRepo.deleteById(id);
    }

    @Override
    public UserDto updateUserById(Long id, UserDto userDto) {
        var userEntity = userRepo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User Entity not found,id:" + id));

        var updatedEntity = UserEntity.builder()
                .id(userEntity.getId())
                .email(userDto.getEmail() != null ? userDto.getEmail() : userEntity.getEmail())
                .password(userDto.getPassword() != null ? userDto.getPassword() : userEntity.getPassword())
                .profiles(userDto.getProfiles() != null ? profileMapper.profileDtoListToProfileEntityList(userDto.getProfiles()) : userEntity.getProfiles())
                .roles(userDto.getRoles() != null ? userDto.getRoles() : userEntity.getRoles())
                .status(userDto.getStatus() != null ? userDto.getStatus() : userEntity.getStatus())
                .username(userDto.getUsername() != null ? userDto.getUsername() : userEntity.getUsername())
                .build();

        return userMapper.userEntityToUserDto(userRepo.save(updatedEntity));
    }

    @Override
    public UserDto createUser(UserDto userDto) {
        var userEntity = userMapper.userDtoToUserEntity(userDto);
        UserEntity saved = userRepo.save(userEntity);
        return userMapper.userEntityToUserDto(saved);
    }

    @Override
    public List<ProfileDto> getAllProfilesById(Long UserId) {
        var userEntity = userRepo.findById(UserId)
                .orElseThrow(() -> new EntityNotFoundException("User Entity not found,id:" + UserId));

        return profileMapper.profileEntityListToProfileDtoList(userEntity.getProfiles());
    }

    @Override
    public ProfileDto getProfileById(Long UserId, Long profileId) {
        var userEntity = userRepo.findById(UserId)
                .orElseThrow(() -> new EntityNotFoundException("User Entity not found,id:" + UserId));

        var profileEntities = userEntity.getProfiles();
        var profileDto = profileMapper.profileEntityToProfileDto(profileEntities.get(profileId.intValue() - 1));

        return profileDto;
    }

    @Override
    public void deleteProfileById(Long UserId, Long profileId) {
        var userEntity = userRepo.findById(UserId).orElseThrow(() -> new EntityNotFoundException("User Entity not found,id:" + UserId));
        var profileEntities = userEntity.getProfiles();
        profileEntities.remove(profileId.intValue() - 1);
    }

    @Override
    public void updateProfileById(Long userId, Long profileId, ProfileDto profileDto) {

        UserEntity user = userRepo.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User not found with id: " + userId));

        ProfileEntity profile = user.getProfiles().stream()
                .filter(p -> p.getId().equals(profileId))
                .findFirst()
                .orElseThrow(() -> new EntityNotFoundException("Profile not found with id: " + profileId));

        profile.setId(profile.getId());
        if (profileDto.getUser_id() != null) {
            profile.setUser_id(profileDto.getUser_id());
        }
        if (profileDto.getProfilePicture() != null) {
            profile.setProfilePicture(profileDto.getProfilePicture());
        }
        if (profileDto.getPlatformsAndUrls() != null) {
            profile.setPlatformsAndUrls(profileDto.getPlatformsAndUrls());
        }

        userRepo.save(user);
    }

    @Override
    public byte[] getProfileImageById(Long userId, Long profileId) {
        UserEntity user = userRepo.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User not found with id: " + userId));

        ProfileEntity profile = user.getProfiles().stream()
                .filter(p -> p.getId().equals(profileId))
                .findFirst()
                .orElseThrow(() -> new EntityNotFoundException("Profile not found with id: " + profileId));

        return profile.getProfilePicture();

    }

    @Override
    public void updateProfileImageById(Long userId, Long profileId, MultipartFile profileImage) {
        if (profileImage != null) {
            UserEntity user = userRepo.findById(userId)
                    .orElseThrow(() -> new EntityNotFoundException("User not found with id: " + userId));

            ProfileEntity profile = user.getProfiles().stream()
                    .filter(p -> p.getId().equals(profileId))
                    .findFirst()
                    .orElseThrow(() -> new EntityNotFoundException("Profile not found with id: " + profileId));

            try {
                profile.setProfilePicture(profileImage.getBytes());
                userRepo.save(user);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
