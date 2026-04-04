package za.co.watchdog.features.authManagement.presentation.mapper;

import org.springframework.stereotype.Component;
import za.co.watchdog.features.authManagement.domain.model.AuthenticatedUser;
import za.co.watchdog.features.authManagement.presentation.model.auth.AuthResponseDto;

@Component
public class AuthPresenterMapper {
    public AuthResponseDto mapToAuthtResponseDto(AuthenticatedUser authenticatedUser) {
        return new AuthResponseDto(
                authenticatedUser.userId(),
                authenticatedUser.emailAddress(),
                authenticatedUser.token(),
                authenticatedUser.userRole(),
                authenticatedUser.authenticatedAt()
        );
    }
}
