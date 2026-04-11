package za.co.watchdog.features.authManagement.domain.usecase;

import org.springframework.stereotype.Service;
import za.co.watchdog.common.domain.model.User;
import za.co.watchdog.common.domain.usecase.UseCase;
import za.co.watchdog.common.domain.exception.ResourceNotFoundException;
import za.co.watchdog.features.authManagement.domain.repository.AuthManagementRepository;

@Service
public class FetchUserByIdUseCase implements UseCase<Long, User> {
    private final AuthManagementRepository authManagementRepository;

    FetchUserByIdUseCase(AuthManagementRepository authManagementRepository) {
        this.authManagementRepository = authManagementRepository;
    }

    @Override
    public User execute(Long input) {
        return authManagementRepository.fetchUserById(input)
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", input));
    }
}
