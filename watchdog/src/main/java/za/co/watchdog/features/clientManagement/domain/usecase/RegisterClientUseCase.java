package za.co.watchdog.features.clientManagement.domain.usecase;

import org.springframework.stereotype.Component;
import za.co.watchdog.common.domain.common.Result;
import za.co.watchdog.common.domain.usecase.UseCase;
import za.co.watchdog.features.clientManagement.domain.model.Client;
import za.co.watchdog.features.clientManagement.domain.repository.ClientManagementRepository;

@Component
public class RegisterClientUseCase implements UseCase<Client, Result<Client>> {
    private final ClientManagementRepository clientManagementRepository;

    public RegisterClientUseCase(ClientManagementRepository clientManagementRepository) {
        this.clientManagementRepository = clientManagementRepository;
    }

    @Override
    public Result<Client> execute(Client input) {
        Result<Client> result = this.clientManagementRepository.fetchClient(input);
        switch (result) {
            case Result.Success<Client> success -> {
                return Result.error("Account already exists");
            }

            case Result.Error<Client> error -> {
                Result<Client> registerClientResult = this.clientManagementRepository.registerClient(input);
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
