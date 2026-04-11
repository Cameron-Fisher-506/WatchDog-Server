package za.co.watchdog.features.authManagement.domain.usecase;

import org.springframework.stereotype.Service;
import za.co.watchdog.common.domain.manager.SecurityManager;
import za.co.watchdog.common.domain.model.User;
import za.co.watchdog.common.domain.usecase.UseCase;
import za.co.watchdog.common.domain.exception.ResourceNotFoundException;
import za.co.watchdog.features.authManagement.domain.repository.AuthManagementRepository;

@Service
public class LoginUserUseCase implements UseCase<User, User> {
    private final AuthManagementRepository authManagementRepository;
    private final SecurityManager securityManager;

    LoginUserUseCase(AuthManagementRepository authManagementRepository, SecurityManager securityManager) {
        this.authManagementRepository = authManagementRepository;
        this.securityManager = securityManager;
    }

    @Override
    public User execute(User input) {
        User user = authManagementRepository.fetchUserByEmailAddress(input.getEmailAddress()).orElseThrow(() -> new ResourceNotFoundException("User", "emailAddress", input.getEmailAddress()));
        if (securityManager.passwordMatches(input.getPassword(), user.getPassword())) {
            return user;
        } else {
            throw new ResourceNotFoundException("User", "emailAddress", input);
        }
    }
}
