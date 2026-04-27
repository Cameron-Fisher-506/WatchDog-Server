package za.co.watchdog.features.clientManagement.domain.usecase;

import org.springframework.stereotype.Component;
import za.co.watchdog.common.domain.exception.ResourceNotFoundException;
import za.co.watchdog.features.incidentManagement.domain.model.Client;
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
    public Client execute(Client input) {
        User user = this.clientManagementRepository.fetchUserById(input.getUserId())
                .orElseThrow(() -> new ResourceNotFoundException("ClientOnboarding", "userId", input.getUserId()));
        if (user.getUserId() != null) {
            return this.clientManagementRepository.onboard(input).orElseThrow(() -> new ResourceNotFoundException("ClientOnboarding", "userId", user.getUserId()));
        } else {
            throw new ResourceNotFoundException("ClientOnboarding", "client", input);
        }
    }
}
