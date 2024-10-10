package com.devaar.backend.model.dto;

import com.devaar.backend.dao.entity.ProfileEntity;
import com.devaar.backend.model.enums.UserRole;
import com.devaar.backend.model.enums.UserStatus;
import lombok.*;

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
