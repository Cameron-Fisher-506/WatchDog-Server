package za.co.watchdog.features.incidentManagement.data.local.dataSource;

import za.co.watchdog.common.data.local.common.DatabaseResponse;
import za.co.watchdog.common.data.local.database.model.*;
import za.co.watchdog.common.domain.model.VehicleStatus;

import java.util.Optional;

public interface IncidentManagementLocalDataSource {
    public DatabaseResponse<IncidentEntity> saveIncidentEntity(IncidentEntity incidentEntity);
    public DatabaseResponse<ClientEntity> fetchClientByUserId(Long userId);
    public Optional<PatrolVehicleEntity> fetchPatrolVehicleByZoneIdAndVehicleStatus(Long zoneId, VehicleStatus vehicleStatus);
    public Optional<LocationEntity> fetchLocationById(Long locationId);
}
