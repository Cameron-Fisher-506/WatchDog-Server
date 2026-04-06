package za.co.watchdog.features.clientManagement.domain.usecase;

import org.springframework.stereotype.Component;
import za.co.watchdog.common.data.manager.security.config.SecurityConfig;
import za.co.watchdog.common.domain.common.Result;
import za.co.watchdog.common.domain.manager.TokenManager;
import za.co.watchdog.common.domain.model.Client;
import za.co.watchdog.common.domain.model.User;
import za.co.watchdog.common.domain.usecase.UseCase;
import za.co.watchdog.features.clientManagement.domain.repository.ClientManagementRepository;

@Component
public class ClientOnboardingUseCase implements UseCase<Client, Result<User>> {
    private final ClientManagementRepository clientManagementRepository;
    private final SecurityConfig securityConfig;
    private final TokenManager tokenManager;

    public ClientOnboardingUseCase(ClientManagementRepository clientManagementRepository, SecurityConfig securityConfig, TokenManager tokenManager) {
        this.clientManagementRepository = clientManagementRepository;
        this.securityConfig = securityConfig;
        this.tokenManager = tokenManager;
    }

    @Override
    public Result<User> execute(Client client) {
        Result<User> result = this.clientManagementRepository.fetchUser(client.getUser());
        return null;
    }
}
