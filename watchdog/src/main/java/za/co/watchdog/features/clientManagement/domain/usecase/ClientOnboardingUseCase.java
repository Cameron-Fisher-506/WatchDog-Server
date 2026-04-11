package za.co.watchdog.features.clientManagement.domain.usecase;

import org.springframework.stereotype.Component;
import za.co.watchdog.common.domain.exception.ResourceNotFoundException;
import za.co.watchdog.common.domain.model.Client;
import za.co.watchdog.common.domain.model.User;
import za.co.watchdog.common.domain.usecase.UseCase;
import za.co.watchdog.features.clientManagement.domain.repository.ClientManagementRepository;

@Component
public class ClientOnboardingUseCase implements UseCase<Client, Client> {
    private final ClientManagementRepository clientManagementRepository;


    public ClientOnboardingUseCase(ClientManagementRepository clientManagementRepository) {
        this.clientManagementRepository = clientManagementRepository;
    }

    @Override
    public Client execute(Client client) {
        User user = this.clientManagementRepository.fetchUserById(client.getUserId())
                .orElseThrow(() -> new ResourceNotFoundException("ClientOnboarding", "userId", client.getUserId()));
        if (user.getUserId() != null) {
            return this.clientManagementRepository.onboard(client)
                    .orElseThrow(() -> new ResourceNotFoundException("ClientOnboarding", "userId", user.getUserId()));
        } else {
            throw new ResourceNotFoundException("ClientOnboarding", "client", client);
        }
    }
}
