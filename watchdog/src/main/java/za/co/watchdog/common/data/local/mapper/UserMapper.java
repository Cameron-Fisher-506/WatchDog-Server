package za.co.watchdog.common.data.local.mapper;

import org.springframework.stereotype.Component;
import za.co.watchdog.common.data.local.database.dao.UserDao;
import za.co.watchdog.common.data.local.database.model.*;
import za.co.watchdog.common.domain.model.Device;
import za.co.watchdog.common.domain.model.User;

@Component
public class UserMapper {
    public User mapToUser(UserEntity userEntity) {
        return User.builder()
                .userId(userEntity.getUserId())
                .emailAddress(userEntity.getEmailAddress())
                .password(userEntity.getPasswordHash())
                .createdAt(userEntity.getCreatedAt())
                .userStatus(mapToUserStatus(userEntity.getUserStatus()))
                .userRole(mapToUserRole(userEntity.getUserRole()))
                .verificationCode(userEntity.getVerificationCode())
                .verificationCodeExpiresAt(userEntity.getVerificationCodeExpiresAt())
                .build();
    }

    public UserEntity mapToUserEntity(User user) {
        return UserEntity.builder()
                .userId(user.getUserId())
                .accountStatus(mapToAccountStatus(user.getAccountStatus()))
                .emailAddress(user.getEmailAddress())
                .passwordHash(user.getPassword())
                .userStatus(mapToUserStatus(user.getUserStatus()))
                .createdAt(user.getCreatedAt())
                .userRole(mapToUserRole(user.getUserRole() != null ? user.getUserRole() : za.co.watchdog.common.domain.model.UserRole.UNKNOWN))
                .verificationCode(user.getVerificationCode())
                .verificationCodeExpiresAt(user.getVerificationCodeExpiresAt())
                .build();
    }

    private UserRole mapToUserRole(za.co.watchdog.common.domain.model.UserRole userRole) {
        return za.co.watchdog.common.data.local.database.model.UserRole.valueOf(userRole.name());
    }

    private za.co.watchdog.common.domain.model.UserRole mapToUserRole(UserRole userRole) {
        return za.co.watchdog.common.domain.model.UserRole.valueOf(userRole.name());
    }

    private UserStatus mapToUserStatus(za.co.watchdog.common.domain.model.UserStatus userStatus) {
        return UserStatus.valueOf(userStatus.name());
    }

    private za.co.watchdog.common.domain.model.UserStatus mapToUserStatus(UserStatus userStatus) {
        return za.co.watchdog.common.domain.model.UserStatus.valueOf(userStatus.name());
    }

    private AccountStatus mapToAccountStatus(za.co.watchdog.common.domain.model.AccountStatus accountStatus) {
        return AccountStatus.valueOf(accountStatus.name());
    }

    private za.co.watchdog.common.domain.model.AccountStatus mapToAccountStatus(AccountStatus accountStatus) {
        return za.co.watchdog.common.domain.model.AccountStatus.valueOf(accountStatus.name());
    }
}
