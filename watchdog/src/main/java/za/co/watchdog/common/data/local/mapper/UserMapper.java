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
                .accountStatus(userEntity.getAccountStatus())
                .password(userEntity.getPasswordHash())
                .createdAt(userEntity.getCreatedAt())
                .userStatus(userEntity.getUserStatus())
                .userRole(userEntity.getUserRole())
                .verificationCode(userEntity.getVerificationCode())
                .verificationCodeExpiresAt(userEntity.getVerificationCodeExpiresAt())
                .build();
    }

    public UserEntity mapToUserEntity(User user) {
        return UserEntity.builder()
                .userId(user.getUserId())
                .accountStatus(user.getAccountStatus())
                .emailAddress(user.getEmailAddress())
                .passwordHash(user.getPassword())
                .userStatus(user.getUserStatus())
                .createdAt(user.getCreatedAt())
                .userRole(user.getUserRole())
                .verificationCode(user.getVerificationCode())
                .verificationCodeExpiresAt(user.getVerificationCodeExpiresAt())
                .build();
    }
}
