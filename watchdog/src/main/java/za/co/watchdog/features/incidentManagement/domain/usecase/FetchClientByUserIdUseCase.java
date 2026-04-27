package za.co.watchdog.features.incidentManagement.domain.usecase;

import org.springframework.stereotype.Service;
import za.co.watchdog.common.domain.exception.ResourceNotFoundException;
import za.co.watchdog.common.domain.usecase.UseCase;
import za.co.watchdog.features.incidentManagement.domain.model.Client;
import za.co.watchdog.features.incidentManagement.domain.repository.IncidentManagementRepository;

@Service
public class FetchClientByUserIdUseCase implements UseCase<Long, Client> {
    private final IncidentManagementRepository incidentManagementRepository;

    FetchClientByUserIdUseCase(IncidentManagementRepository incidentManagementRepository) {
        this.incidentManagementRepository = incidentManagementRepository;
    }

    @Override
    public Client execute(Long input) {
        return incidentManagementRepository.fetchClientBuUserId(input).orElseThrow(() -> new ResourceNotFoundException("FetchClientByUserId", "userId", input));
    }
}
