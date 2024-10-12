package com.devaar.bff.controller;

import com.devaar.bff.model.dto.ProfileDto;
import com.devaar.bff.service.BffService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/v1/users/{userId}/profiles")
@RequiredArgsConstructor
public class ProfileController {

    private final BffService bffService;

    @GetMapping
    public ResponseEntity<List<ProfileDto>> getAllProfilesById(@PathVariable Long userId) {
        List<ProfileDto> profiles = bffService.getAllProfilesById(userId);
        return ResponseEntity.ok(profiles);
    }

     @GetMapping("/{profileId}")
    public ResponseEntity<ProfileDto> getProfileById(@PathVariable Long userId,
                                                     @PathVariable Long profileId) {
        ProfileDto profile = bffService.getProfileById(userId, profileId);
        return ResponseEntity.ok(profile);
    }

     @PutMapping("/{profileId}")
    public ResponseEntity<Void> updateProfileById(
            @PathVariable Long userId,
            @PathVariable Long profileId,
            @Valid @RequestBody ProfileDto profileDto) {
        bffService.updateProfileById(userId, profileId, profileDto);
        return ResponseEntity.ok().build();
    }

     @DeleteMapping("/{profileId}")
    public ResponseEntity<Void> deleteProfileById(
            @PathVariable Long userId, @PathVariable Long profileId) {
        bffService.deleteProfileById(userId, profileId);
        return ResponseEntity.noContent().build();
    }

     @GetMapping("/{profileId}/image")
    public ResponseEntity<byte[]> getProfileImageById(
            @PathVariable Long userId, @PathVariable Long profileId) {
        byte[] image = bffService.getProfileImageById(userId, profileId);
        return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(image);
    }

     @PatchMapping("/{profileId}/image")
    public ResponseEntity<Void> updateProfileImageById(
            @PathVariable Long userId, @PathVariable Long profileId,
            @RequestParam("image") @Valid MultipartFile profileImage) throws IOException {
        bffService.updateProfileImageById(userId, profileId, profileImage);
        return ResponseEntity.ok().build();
    }
}

