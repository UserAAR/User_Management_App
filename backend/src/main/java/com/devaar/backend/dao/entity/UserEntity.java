package com.devaar.backend.dao.entity;

import com.devaar.backend.model.enums.UserRole;
import com.devaar.backend.model.enums.UserStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString(callSuper = true)
@SuperBuilder
@Table(name = "system_user_app")
public class UserEntity extends BaseEntity {

    @NotBlank(message = "Username is required")
    @Column
    private String username;

    @NotBlank(message = "Password is required")
    @Column
    @Size(min = 8, message = "Password must be at least 8 characters long")
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$",
            message = "Password must contain at least one uppercase letter, one lowercase letter, one number, and one special character")
    private String password;

    @NotBlank(message = "Email is required")
    @Email(message = "Email should be valid")
    @Column(unique = true)
    private String email;

    @Enumerated(value = EnumType.STRING)
    @Column
    private UserStatus status;

    @ElementCollection(targetClass = UserRole.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id"))
    @Column(name = "role")
    @Enumerated(value = EnumType.STRING)
    private List<UserRole> roles;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
//    @JoinTable(
//            name = "",
//            joinColumns = @JoinColumn(name = ""),
//            inverseJoinColumns = @JoinColumn(name = "")
//    )
    private List<ProfileEntity> profiles;


    @PrePersist
    public void prePersist() {
        if (status == null) {
            status = UserStatus.ACTIVE;
        }
    }
}
