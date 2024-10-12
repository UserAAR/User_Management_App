package com.devaar.backend.controller;

import com.devaar.backend.model.dto.ProfileDto;
import com.devaar.backend.model.dto.UserDto;
import com.devaar.backend.service.concretes.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/backend/users")
public class UserController {

    private final UserService userService;

    @GetMapping
    public ResponseEntity<List<UserDto>> getAllUsers() {
        List<UserDto> users = userService.getAllUsers();
        return ResponseEntity.ok(users);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable Long id) {
        UserDto user = userService.getUserById(id);
        return ResponseEntity.ok(user);
    }

    @PostMapping
    public ResponseEntity<UserDto> createUser(@RequestBody UserDto userDto) {
        UserDto newUser = userService.createUser(userDto);
        return new ResponseEntity<>(newUser, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserDto> updateUserById(@PathVariable Long id, @RequestBody UserDto userDto) {
        UserDto updatedUser = userService.updateUserById(id, userDto);
        return ResponseEntity.ok(updatedUser);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUserById(@PathVariable Long id) {
        userService.deleteUserById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{userId}/profiles")
    public ResponseEntity<List<ProfileDto>> getAllProfilesById(@PathVariable Long userId) {
        List<ProfileDto> profiles = userService.getAllProfilesById(userId);
        return ResponseEntity.ok(profiles);
    }

    // Get profile by user id and profile id
    @GetMapping("/{userId}/profiles/{profileId}")
    public ResponseEntity<ProfileDto> getProfileById(@PathVariable Long userId, @PathVariable Long profileId) {
        ProfileDto profile = userService.getProfileById(userId, profileId);
        return ResponseEntity.ok(profile);
    }

    @DeleteMapping("/{userId}/profiles/{profileId}")
    public ResponseEntity<Void> deleteProfileById(@PathVariable Long userId, @PathVariable Long profileId) {
        userService.deleteProfileById(userId, profileId);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{userId}/profiles/{profileId}")
    public ResponseEntity<Void> updateProfileById(@PathVariable Long userId, @PathVariable Long profileId, @RequestBody ProfileDto profileDto) {
        userService.updateProfileById(userId, profileId, profileDto);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{userId}/profiles/{profileId}/image")
    public ResponseEntity<byte[]> getProfileImageById(
            @PathVariable Long userId, @PathVariable Long profileId) {
        byte[] image = userService.getProfileImageById(userId, profileId);
        return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(image);
    }

    @PatchMapping("/{userId}/profiles/{profileId}/image")
    public void updateProfileImageById(@PathVariable Long userId,
                                                       @PathVariable Long profileId,
                                                       @RequestParam MultipartFile profileImage) {
        userService.updateProfileImageById(userId, profileId, profileImage);
    }
}
