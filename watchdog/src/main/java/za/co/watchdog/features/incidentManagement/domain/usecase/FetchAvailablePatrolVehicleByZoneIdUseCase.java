package za.co.watchdog.features.incidentManagement.domain.usecase;

import org.springframework.stereotype.Service;
import za.co.watchdog.common.domain.exception.ResourceNotFoundException;
import za.co.watchdog.common.domain.model.VehicleStatus;
import za.co.watchdog.common.domain.usecase.UseCase;
import za.co.watchdog.features.incidentManagement.domain.model.PatrolVehicle;
import za.co.watchdog.features.incidentManagement.domain.repository.IncidentManagementRepository;

@Service
public class FetchAvailablePatrolVehicleByZoneIdUseCase implements UseCase<Long, PatrolVehicle> {
    private final IncidentManagementRepository incidentManagementRepository;

    FetchAvailablePatrolVehicleByZoneIdUseCase(IncidentManagementRepository incidentManagementRepository) {
        this.incidentManagementRepository = incidentManagementRepository;
    }

    @Override
    public PatrolVehicle execute(Long input) {
        return incidentManagementRepository.fetchPatrolVehicleByZoneIdAndVehicleStatus(input, VehicleStatus.AVAILABLE)
                .orElseThrow(() -> new ResourceNotFoundException("PatrolVehicle", "zoneId", input));
    }
}
