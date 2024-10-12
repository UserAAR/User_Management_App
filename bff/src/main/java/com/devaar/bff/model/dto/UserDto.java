package com.devaar.bff.model.dto;

import com.devaar.bff.model.enums.UserRole;
import com.devaar.bff.model.enums.UserStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {

    private Long id;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private String username;
    private String password;
    private String email;
    private UserStatus status;
    private List<UserRole> roles;
    private List<ProfileDto> profiles;
}
