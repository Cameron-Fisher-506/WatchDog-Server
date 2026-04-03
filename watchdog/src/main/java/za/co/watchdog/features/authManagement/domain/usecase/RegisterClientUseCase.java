package za.co.watchdog.features.authManagement.domain.usecase;

import org.antlr.v4.runtime.Token;
import org.springframework.stereotype.Component;
import za.co.watchdog.common.domain.common.Result;
import za.co.watchdog.common.domain.manager.TokenManager;
import za.co.watchdog.common.domain.usecase.UseCase;
import za.co.watchdog.common.data.manager.security.config.SecurityConfig;
import za.co.watchdog.features.authManagement.domain.model.AuthenticatedUser;
import za.co.watchdog.features.authManagement.domain.model.Client;
import za.co.watchdog.features.authManagement.domain.repository.ClientManagementRepository;

@Component
public class RegisterClientUseCase implements UseCase<Client, Result<AuthenticatedUser>> {
    private final ClientManagementRepository clientManagementRepository;
    private final SecurityConfig securityConfig;
    private final TokenManager tokenManager;

    public RegisterClientUseCase(ClientManagementRepository clientManagementRepository, SecurityConfig securityConfig, TokenManager tokenManager) {
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
                        return Result.success(new AuthenticatedUser(input.getUserId(), input.getEmailAddress(), tokenManager.generateToken(input.getEmailAddress(), input.getUserRole().name()), input.getUserRole(), input.getCreatedAt()));
                    }

                    case Result.Error<Client> resgisterClientError -> {
                        return Result.error(resgisterClientError.message());
                    }
                }
            }
        }
    }
}
