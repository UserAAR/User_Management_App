package com.devaar.backend.model.enums;

public enum UserStatus {
    ACTIVE,
    INACTIVE,
    BANNED,
    PENDING,      // Kullanıcı kaydı tamamlanmamış, onay bekliyor
    DELETED,
}
