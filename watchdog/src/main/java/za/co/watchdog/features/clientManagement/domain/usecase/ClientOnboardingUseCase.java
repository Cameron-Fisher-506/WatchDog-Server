package za.co.watchdog.features.clientManagement.domain.usecase;

import org.springframework.stereotype.Component;
import za.co.watchdog.common.data.manager.security.config.SecurityConfig;
import za.co.watchdog.common.domain.common.Result;
import za.co.watchdog.common.domain.manager.TokenManager;
import za.co.watchdog.common.domain.model.Client;
import za.co.watchdog.common.domain.usecase.UseCase;
import za.co.watchdog.features.authManagement.domain.model.AuthenticatedUser;
import za.co.watchdog.features.clientManagement.domain.repository.ClientManagementRepository;

@Component
public class ClientOnboardingUseCase implements UseCase<Client, Result<AuthenticatedUser>> {
    private final ClientManagementRepository clientManagementRepository;
    private final SecurityConfig securityConfig;
    private final TokenManager tokenManager;

    public ClientOnboardingUseCase(ClientManagementRepository clientManagementRepository, SecurityConfig securityConfig, TokenManager tokenManager) {
        this.clientManagementRepository = clientManagementRepository;
        this.securityConfig = securityConfig;
        this.tokenManager = tokenManager;
    }

    @Override
    public Result<AuthenticatedUser> execute(Client input) {
        Result<Client> result = this.clientManagementRepository.fetchClient(input);
        switch (result) {
            case Result.Success<Client> success -> {
                return Result.error("Account already exists");
            }

            case Result.Error<Client> error -> {
                Client client = input.copyWith(securityConfig.passwordEncoder().encode(input.getPassword()));
                Result<Client> registerClientResult = this.clientManagementRepository.registerClient(client);
                switch (registerClientResult) {
                    case Result.Success<Client> success -> {
                        return Result.success(new AuthenticatedUser(success.data().getUserId(),
                                success.data().getEmailAddress(),
                                tokenManager.generateToken(success.data().getEmailAddress(),
                                        success.data().getUserRole().name()), success.data().getUserRole(), success.data().getCreatedAt()));
                    }

                    case Result.Error<Client> resgisterClientError -> {
                        return Result.error(resgisterClientError.message());
                    }
                }
            }
        }
    }
}
