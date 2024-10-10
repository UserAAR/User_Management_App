package com.devaar.backend.dao.entity;

import com.devaar.backend.model.enums.Platform;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;


import java.util.Map;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString(callSuper = true)
@SuperBuilder
@Table(name = "profile")
public class ProfileEntity extends BaseEntity {

    @ElementCollection
    @MapKeyEnumerated(EnumType.STRING)
    @MapKeyColumn(name = "platform")
    @Column(name = "url")
    private Map<Platform, String> platformsAndUrls;

    @Column
    private Long user_id;

    @Column
    private byte[] profilePicture;
}
