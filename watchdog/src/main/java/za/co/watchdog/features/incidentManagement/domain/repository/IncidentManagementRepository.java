package za.co.watchdog.features.incidentManagement.domain.repository;

import za.co.watchdog.common.domain.model.VehicleStatus;
import za.co.watchdog.features.incidentManagement.domain.model.*;

import java.util.Optional;

public interface IncidentManagementRepository {
    public Optional<Incident> saveIncident(Incident incident);
    public Optional<Client> fetchClientByUserId(Long userId);
    public Optional<PatrolVehicle> fetchPatrolVehicleByZoneIdAndVehicleStatus(Long zoneId, VehicleStatus vehicleStatus);
    public Optional<Address> fetchAddressById(Long addressId);
}
