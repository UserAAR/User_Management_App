package com.devaar.bff.client;

import com.devaar.bff.model.dto.ProfileDto;
import com.devaar.bff.model.dto.UserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Component
@RequiredArgsConstructor
public class BffClient {

    private final RestTemplate restTemplate;

    private final String backendApiUrl =
            "http://localhost:8080/api/v1/backend/users";

    public List<UserDto> getAllUsers() {
        ResponseEntity<List<UserDto>> response = restTemplate.exchange(
                backendApiUrl,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<>() {
                }
        );
        return response.getBody();
    }

    public UserDto getUserById(Long id) {
        String url = backendApiUrl + "/" + id;
        return restTemplate.getForObject(url, UserDto.class);
    }

    public UserDto createUser(UserDto userDto) {
        return restTemplate.postForObject(backendApiUrl, userDto, UserDto.class);
    }

    public void updateUserById(Long id, UserDto userDto) {
        String url = backendApiUrl + "/" + id;
        restTemplate.put(url, userDto);
    }

    public void deleteUserById(Long id) {
        String url = backendApiUrl + "/" + id;
        restTemplate.delete(url);
    }

    public List<ProfileDto> getAllProfilesById(Long userId) {
        String url = backendApiUrl + "/" + userId + "/profiles";
        ResponseEntity<List<ProfileDto>> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<>() {
                }
        );
        return response.getBody();
    }

    public ProfileDto getProfileById(Long userId, Long profileId) {
        String url = backendApiUrl + "/" + userId + "/profiles/" + profileId;
        return restTemplate.getForObject(url, ProfileDto.class);
    }

    public void deleteProfileById(Long userId, Long profileId) {
        String url = backendApiUrl + "/" + userId + "/profiles/" + profileId;
        restTemplate.delete(url);
    }

    public void updateProfileById(Long userId, Long profileId, ProfileDto profileDto) {
        String url = backendApiUrl + "/" + userId + "/profiles/" + profileId;
        restTemplate.put(url, profileDto);
    }

    public byte[] getProfileImageById(Long userId, Long profileId) {
        String url = backendApiUrl + "/" + userId + "/profiles/" + profileId + "/image";
        ResponseEntity<byte[]> response = restTemplate.getForEntity(url, byte[].class);
        return response.getBody();
    }

    public void updateProfileImageById(Long userId, Long profileId, MultipartFile profileImage) throws IOException {
        String url = backendApiUrl + "/" + userId + "/profiles/" + profileId + "/image";

        MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();

        body.add("image", new ByteArrayResource(profileImage.getBytes()) {
            @Override
            public String getFilename() {
                return profileImage.getOriginalFilename();
            }
        });

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);

        HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(body, headers);

        restTemplate.exchange(url, HttpMethod.PATCH, requestEntity, Void.class);
    }

}
