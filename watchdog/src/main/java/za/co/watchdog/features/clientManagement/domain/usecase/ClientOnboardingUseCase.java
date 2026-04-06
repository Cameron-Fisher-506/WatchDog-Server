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
public class ClientOnboardingUseCase implements UseCase<Client, Result<Client>> {
    private final ClientManagementRepository clientManagementRepository;


    public ClientOnboardingUseCase(ClientManagementRepository clientManagementRepository) {
        this.clientManagementRepository = clientManagementRepository;
    }

    @Override
    public Result<Client> execute(Client client) {
        Result<User> userResult = this.clientManagementRepository.fetchUser(client.getUser());
        switch (userResult) {
            case Result.Success<User> userSuccess -> {
                Result<Client> clientResult = this.clientManagementRepository.onboard(client);
                switch (clientResult) {
                    case Result.Success<Client> clientSuccess -> {
                        return Result.success(clientSuccess.data());
                    }

                    case Result.Error<Client> clientError -> {
                        return Result.error(clientError.message());
                    }
                }
            }

            case Result.Error<User> userError -> {
                return Result.error(userError.message());
            }
        }
    }
}
