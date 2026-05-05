package za.co.watchdog.features.incidentManagement.domain.repository;

import za.co.watchdog.common.domain.model.VehicleStatus;
import za.co.watchdog.features.incidentManagement.domain.model.Client;
import za.co.watchdog.features.incidentManagement.domain.model.Incident;
import za.co.watchdog.features.incidentManagement.domain.model.Location;
import za.co.watchdog.features.incidentManagement.domain.model.PatrolVehicle;

import java.util.Optional;

public interface IncidentManagementRepository {
    public Optional<Incident> saveIncident(Incident incident);
    public Optional<Client> fetchClientBuUserId(Long userId);
    public Optional<PatrolVehicle> fetchPatrolVehicleByZoneIdAndVehicleStatus(Long zoneId, VehicleStatus vehicleStatus);
    public Optional<Location> fetchLocationById(Long locationId);
}
