package za.co.watchdog.features.authManagement.domain.usecase;

import org.springframework.stereotype.Service;
import za.co.watchdog.common.domain.exception.ResourceNotFoundException;
import za.co.watchdog.common.domain.manager.SecurityManager;
import za.co.watchdog.common.domain.model.User;
import za.co.watchdog.common.domain.usecase.UseCase;
import za.co.watchdog.features.authManagement.domain.repository.AuthManagementRepository;
import java.util.Optional;

@Service
public class RegisterUserUseCase implements UseCase<User, User> {
    private final AuthManagementRepository authManagementRepository;
    private final SecurityManager securityManager;

    public RegisterUserUseCase(AuthManagementRepository authManagementRepository, SecurityManager securityManager) {
        this.authManagementRepository = authManagementRepository;
        this.securityManager = securityManager;
    }

    @Override
    public User execute(User input) {
        Optional<User> optionalUser = this.authManagementRepository.fetchUserByEmailAddress(input.getEmailAddress());
        if (optionalUser.isEmpty()) {
            input.setPassword(securityManager.encode(input.getPassword()));
            return this.authManagementRepository.register(input)
                    .orElseThrow(() -> new ResourceNotFoundException("RegisterUser", "userId", input.getUserId()));
        } else {
            throw new ResourceNotFoundException("RegisterUser", "userId", input.getUserId());
        }
    }
}
