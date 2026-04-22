package za.co.watchdog.common.domain.usecase;

import org.springframework.stereotype.Service;
import za.co.watchdog.common.domain.exception.ResourceNotFoundException;
import za.co.watchdog.common.domain.manager.SecurityManager;
import za.co.watchdog.common.domain.model.User;
import za.co.watchdog.common.domain.repository.UserManagementRepository;

@Service
public class FetchCurrentUserByUsernameUseCase implements UseCase<Void, User> {
    private final UserManagementRepository userManagementRepository;
    private final SecurityManager securityManager;

    FetchCurrentUserByUsernameUseCase(UserManagementRepository userManagementRepository, SecurityManager securityManager) {
        this.userManagementRepository = userManagementRepository;
        this.securityManager = securityManager;
    }

    @Override
    public User execute(Void input) {
        return userManagementRepository.fetchUserByUsername(securityManager.getCurrentUsername())
                .orElseThrow(() -> new ResourceNotFoundException("FetchCurrentUser", "username", securityManager.getCurrentUsername()));
    }
}
