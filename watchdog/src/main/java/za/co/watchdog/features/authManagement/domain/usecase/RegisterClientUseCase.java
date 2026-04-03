package za.co.watchdog.features.authManagement.domain.usecase;

import org.springframework.stereotype.Component;
import za.co.watchdog.common.domain.common.Result;
import za.co.watchdog.common.domain.usecase.UseCase;
import za.co.watchdog.common.data.manager.security.config.SecurityConfig;
import za.co.watchdog.features.authManagement.domain.model.Client;
import za.co.watchdog.features.authManagement.domain.repository.ClientManagementRepository;

@Component
public class RegisterClientUseCase implements UseCase<Client, Result<Client>> {
    private final ClientManagementRepository clientManagementRepository;
    private final SecurityConfig securityConfig;

    public RegisterClientUseCase(ClientManagementRepository clientManagementRepository, SecurityConfig securityConfig) {
        this.clientManagementRepository = clientManagementRepository;
        this.securityConfig = securityConfig;
    }

    @Override
    public Result<Client> execute(Client input) {
        Result<Client> result = this.clientManagementRepository.fetchClient(input);
        switch (result) {
            case Result.Success<Client> success -> {
                return Result.error("Account already exists");
            }

            case Result.Error<Client> error -> {
                Client client = input.copyWith(securityConfig.passwordEncoder().encode(input.user().password()));
                Result<Client> registerClientResult = this.clientManagementRepository.registerClient(client);
                switch (registerClientResult) {
                    case Result.Success<Client> success -> {
                        return Result.success(success.data());
                    }

                    case Result.Error<Client> resgisterClientError -> {
                        return Result.error(resgisterClientError.message());
                    }
                }
            }
        }
    }
}
