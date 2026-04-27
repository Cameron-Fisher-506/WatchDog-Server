package za.co.watchdog.features.incidentManagement.domain.usecase;

import org.springframework.stereotype.Service;
import za.co.watchdog.common.domain.exception.ResourceNotFoundException;
import za.co.watchdog.common.domain.usecase.UseCase;
import za.co.watchdog.features.incidentManagement.domain.model.Location;
import za.co.watchdog.features.incidentManagement.domain.repository.IncidentManagementRepository;

@Service
public class FetchLocationByIdUseCase implements UseCase<Long, Location> {
    private final IncidentManagementRepository incidentManagementRepository;

    FetchLocationByIdUseCase(IncidentManagementRepository incidentManagementRepository) {
        this.incidentManagementRepository = incidentManagementRepository;
    }

    @Override
    public Location execute(Long input) {
        return incidentManagementRepository.fetchLocationById(input).orElseThrow(() -> new ResourceNotFoundException("Location", "locationId", input));
    }
}
