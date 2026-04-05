package za.co.watchdog.features.authManagement.presentation.mapper;

import org.springframework.stereotype.Component;
import za.co.watchdog.common.domain.model.User;
import za.co.watchdog.features.authManagement.domain.model.AuthenticatedUser;
import za.co.watchdog.features.authManagement.presentation.model.AuthResponseDto;
import za.co.watchdog.features.authManagement.presentation.model.register.RegisterRequestDto;
import za.co.watchdog.features.clientManagement.presentation.model.clientOnboarding.dto.UserRole;

@Component
public class AuthPresenterMapper {
    public AuthResponseDto mapToAuthResponseDto(AuthenticatedUser authenticatedUser) {
        return new AuthResponseDto(
                authenticatedUser.userId(),
                authenticatedUser.emailAddress(),
                authenticatedUser.token(),
                authenticatedUser.userRole(),
                authenticatedUser.authenticatedAt()
        );
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

    private UserRole mapToUserRole(za.co.watchdog.common.domain.model.UserRole userRole) {
        return UserRole.valueOf(userRole.name());
    }

    private za.co.watchdog.common.domain.model.UserRole mapToUserRole(UserRole userRole) {
        return za.co.watchdog.common.domain.model.UserRole.valueOf(userRole.name());
    }
}
