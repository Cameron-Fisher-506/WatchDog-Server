package za.co.watchdog.features.clientManagement.domain.usecase;

import org.springframework.stereotype.Service;
import za.co.watchdog.common.domain.exception.ResourceNotFoundException;
import za.co.watchdog.common.domain.usecase.UseCase;
import za.co.watchdog.features.incidentManagement.domain.model.Location;
import za.co.watchdog.features.incidentManagement.domain.repository.IncidentManagementRepository;

@Service
public class SaveLocationUseCase implements UseCase<Location, Location> {
    private final IncidentManagementRepository incidentManagementRepository;

    SaveLocationUseCase(IncidentManagementRepository incidentManagementRepository) {
        this.incidentManagementRepository = incidentManagementRepository;
    }

    @Override
    public Location execute(Location input) {
        return incidentManagementRepository.saveLocation(input).orElseThrow(() -> new ResourceNotFoundException("SaveLocation", "location", input));
    }
}
