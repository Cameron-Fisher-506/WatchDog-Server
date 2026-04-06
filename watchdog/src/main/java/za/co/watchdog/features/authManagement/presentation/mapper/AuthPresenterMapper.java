package za.co.watchdog.features.authManagement.presentation.mapper;

import org.springframework.stereotype.Component;
import za.co.watchdog.common.domain.manager.TokenManager;
import za.co.watchdog.common.domain.model.User;
import za.co.watchdog.features.authManagement.presentation.model.AuthResponseDto;
import za.co.watchdog.features.authManagement.presentation.model.login.LoginRequestDto;
import za.co.watchdog.features.authManagement.presentation.model.register.RegisterRequestDto;
import za.co.watchdog.common.presentation.model.UserRole;

@Component
public class AuthPresenterMapper {
    private final TokenManager tokenManager;

    AuthPresenterMapper(TokenManager tokenManager) {
        this.tokenManager = tokenManager;
    }

    public AuthResponseDto mapToAuthResponseDto(User user) {
        return AuthResponseDto.builder()
                .userId(user.getUserId())
                .emailAddress(user.getEmailAddress())
                .token(tokenManager.generateToken(user.getEmailAddress(), user.getUserRole().name()))
                .userRole(user.getUserRole())
                .authenticatedAt(user.getCreatedAt())
                .build();
    }

    public User mapToUser(RegisterRequestDto registerRequestDto) {
        return User.builder()
                .userId(registerRequestDto.getUserId())
                .emailAddress(registerRequestDto.getEmailAddress())
                .password(registerRequestDto.getPassword())
                .isActive(registerRequestDto.getIsActive())
                .createdAt(registerRequestDto.getCreatedAt())
                .userRole(mapToUserRole(registerRequestDto.getUserRole()))
                .build();
    }

    public User mapToUser(LoginRequestDto loginRequestDto) {
        return User.builder()
                .emailAddress(loginRequestDto.emailAddress)
                .password(loginRequestDto.password)
                .build();
    }

    private UserRole mapToUserRole(za.co.watchdog.common.domain.model.UserRole userRole) {
        return UserRole.valueOf(userRole.name());
    }

    private za.co.watchdog.common.domain.model.UserRole mapToUserRole(UserRole userRole) {
        return za.co.watchdog.common.domain.model.UserRole.valueOf(userRole.name());
    }
}
