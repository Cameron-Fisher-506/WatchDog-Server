package za.co.watchdog.features.incidentManagement.domain.usecase;

import org.springframework.stereotype.Service;
import za.co.watchdog.common.domain.exception.ResourceNotFoundException;
import za.co.watchdog.common.domain.usecase.UseCase;
import za.co.watchdog.features.incidentManagement.domain.model.Incident;
import za.co.watchdog.features.incidentManagement.domain.repository.IncidentManagementRepository;

@Service
public class CreateIncidentUseCase implements UseCase<Incident, Incident> {
    private final IncidentManagementRepository incidentManagementRepository;

    CreateIncidentUseCase(IncidentManagementRepository incidentManagementRepository) {
        this.incidentManagementRepository = incidentManagementRepository;
    }

    @Override
    public Incident execute(Incident input) {
        return incidentManagementRepository.saveIncident(input).orElseThrow(() -> new ResourceNotFoundException("SaveIncident", "incident", input));
    }
}
