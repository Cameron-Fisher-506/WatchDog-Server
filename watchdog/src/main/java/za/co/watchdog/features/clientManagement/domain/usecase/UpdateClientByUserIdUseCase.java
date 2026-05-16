package za.co.watchdog.features.clientManagement.domain.usecase;

import org.springframework.stereotype.Service;
import za.co.watchdog.common.domain.exception.ResourceNotFoundException;
import za.co.watchdog.common.domain.usecase.UseCase;
import za.co.watchdog.features.clientManagement.domain.model.Client;
import za.co.watchdog.features.clientManagement.domain.repository.ClientManagementRepository;

@Service
public class UpdateClientByUserIdUseCase implements UseCase<Client, Client> {
    private final ClientManagementRepository clientManagementRepository;

    UpdateClientByUserIdUseCase(ClientManagementRepository clientManagementRepository) {
        this.clientManagementRepository = clientManagementRepository;
    }

    @Override
    public Client execute(Client input) {
        return clientManagementRepository.saveClient(input).orElseThrow(() -> new ResourceNotFoundException("Client", "client", input));
    }
}
