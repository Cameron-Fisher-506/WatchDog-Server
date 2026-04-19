package za.co.watchdog.features.incidentManagement.data.repository;

import za.co.watchdog.features.incidentManagement.domain.model.Incident;
import za.co.watchdog.features.incidentManagement.domain.model.Location;
import za.co.watchdog.features.incidentManagement.domain.repository.IncidentManagementRepository;

import java.util.Optional;

public class IncidentManagementRepositoryImpl implements IncidentManagementRepository {
    @Override
    public Optional<Incident> saveIncident(Incident incident) {
        return Optional.empty();
    }

    @Override
    public Optional<Location> saveLocation(Location location) {
        return Optional.empty();
    }
}
