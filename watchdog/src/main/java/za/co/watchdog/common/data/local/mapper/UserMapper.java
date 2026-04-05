package za.co.watchdog.common.data.local.mapper;

import org.springframework.stereotype.Component;
import za.co.watchdog.common.data.local.database.model.UserEntity;
import za.co.watchdog.common.data.local.database.model.UserRole;

@Component
public class UserMapper {
    public User mapToUser(UserEntity userEntity) {
        return User.builder()
                .userId(userEntity.getUserId())
                .emailAddress(userEntity.getEmailAddress())
                .isActive(userEntity.getIsActive())
                .createdAt(userEntity.getCreatedAt())
                .userRole(mapToUserRole(userEntity.getUserRole()))
                .build();
    }

    public UserEntity mapToUserEntity(User user) {
        return UserEntity.builder()
                .userId(user.getUserId())
                .emailAddress(user.getEmailAddress())
                .passwordHash(user.getPassword())
                .isActive(user.getIsActive())
                .createdAt(user.getCreatedAt())
                .userRole(mapToUserRole(user.getUserRole()))
                .build();
    }

    private UserRole mapToUserRole(za.co.watchdog.features.authManagement.domain.model.UserRole userRole) {
        return za.co.watchdog.common.data.local.database.model.UserRole.valueOf(userRole.name());
    }

    private za.co.watchdog.features.authManagement.domain.model.UserRole mapToUserRole(UserRole userRole) {
        return za.co.watchdog.features.authManagement.domain.model.UserRole.valueOf(userRole.name());
    }
}
