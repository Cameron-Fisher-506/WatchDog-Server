package za.co.watchdog.features.authManagement.domain.usecase;

import org.springframework.stereotype.Service;
import za.co.watchdog.common.domain.exception.ResourceNotFoundException;
import za.co.watchdog.common.domain.model.User;
import za.co.watchdog.common.domain.usecase.UseCase;
import za.co.watchdog.features.authManagement.domain.repository.AuthManagementRepository;

@Service
public class SaveUserUseCase implements UseCase<User, User> {
    private final AuthManagementRepository authManagementRepository;

    SaveUserUseCase(AuthManagementRepository authManagementRepository) {
        this.authManagementRepository = authManagementRepository;
    }

    @Override
    public User execute(User input) {
        return authManagementRepository.saveUser(input).orElseThrow(() -> new ResourceNotFoundException("SaveUser", "user", input.getUserId()));
    }
}
