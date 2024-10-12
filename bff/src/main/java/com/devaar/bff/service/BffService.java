package com.devaar.bff.service;

import com.devaar.bff.client.BffClient;
import com.devaar.bff.model.dto.ProfileDto;
import com.devaar.bff.model.dto.UserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BffService {

    private final BffClient bffClient;

    public List<UserDto> getAllUsers() {
        return bffClient.getAllUsers();
    }

    public UserDto getUserById(Long id) {
        return bffClient.getUserById(id);
    }

    public UserDto createUser(UserDto userDto) {
        return bffClient.createUser(userDto);
    }

    public void updateUserById(Long id, UserDto userDto) {
        bffClient.updateUserById(id, userDto);
    }

    public void deleteUserById(Long id) {
        bffClient.deleteUserById(id);
    }

    public List<ProfileDto> getAllProfilesById(Long userId) {
        return bffClient.getAllProfilesById(userId);
    }

    public ProfileDto getProfileById(Long userId, Long profileId) {
        return bffClient.getProfileById(userId, profileId);
    }

    public void deleteProfileById(Long userId, Long profileId) {
        bffClient.deleteProfileById(userId, profileId);
    }

    public void updateProfileById(Long userId, Long profileId, ProfileDto profileDto) {
        bffClient.updateProfileById(userId, profileId, profileDto);
    }

    public byte[] getProfileImageById(Long userId, Long profileId) {
        return bffClient.getProfileImageById(userId, profileId);
    }

    public void updateProfileImageById(Long userId, Long profileId, MultipartFile profileImage) throws IOException {
        bffClient.updateProfileImageById(userId, profileId, profileImage);
    }
}
