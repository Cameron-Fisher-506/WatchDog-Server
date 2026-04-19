package za.co.watchdog.features.incidentManagement.domain.repository;

import za.co.watchdog.features.incidentManagement.domain.model.Incident;
import za.co.watchdog.features.incidentManagement.domain.model.Location;

import java.util.Optional;

public interface IncidentManagementRepository {
    public Optional<Incident> saveIncident(Incident incident);
    public Optional<Location> saveLocation(Location location);
}
