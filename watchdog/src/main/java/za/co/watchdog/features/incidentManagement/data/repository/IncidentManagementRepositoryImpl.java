package za.co.watchdog.features.incidentManagement.data.repository;

import org.springframework.stereotype.Component;
import za.co.watchdog.common.data.local.common.DatabaseResponse;
import za.co.watchdog.common.data.local.database.model.IncidentEntity;
import za.co.watchdog.common.data.local.database.model.LocationEntity;
import za.co.watchdog.features.incidentManagement.data.local.dataSource.IncidentManagementLocalDataSource;
import za.co.watchdog.features.incidentManagement.data.local.mapper.IncidentMapper;
import za.co.watchdog.features.incidentManagement.data.local.mapper.LocationMapper;
import za.co.watchdog.features.incidentManagement.domain.model.Incident;
import za.co.watchdog.features.incidentManagement.domain.model.Location;
import za.co.watchdog.features.incidentManagement.domain.repository.IncidentManagementRepository;
import java.util.Optional;

@Component
public class IncidentManagementRepositoryImpl implements IncidentManagementRepository {
    private final IncidentManagementLocalDataSource incidentManagementLocalDataSource;
    private final IncidentMapper incidentMapper;
    private final LocationMapper locationMapper;

    IncidentManagementRepositoryImpl(IncidentManagementLocalDataSource incidentManagementLocalDataSource, IncidentMapper incidentMapper, LocationMapper locationMapper) {
        this.incidentManagementLocalDataSource = incidentManagementLocalDataSource;
        this.incidentMapper = incidentMapper;
        this.locationMapper = locationMapper;
    }

    @Override
    public Optional<Incident> saveIncident(Incident incident) {
        DatabaseResponse<IncidentEntity> databaseResponse = incidentManagementLocalDataSource.saveIncidentEntity(incidentMapper.mapToIncidentEntity(incident));
        switch (databaseResponse) {
            case DatabaseResponse.Success<IncidentEntity> success -> {
                return Optional.of(incidentMapper.mapToIncident(success.data()));
            }

            case DatabaseResponse.Error<IncidentEntity> error -> {
                return Optional.empty();
            }
        }
    }

    @Override
    public Optional<Location> saveLocation(Location location) {
        DatabaseResponse<LocationEntity> databaseResponse = incidentManagementLocalDataSource.saveLocationEntity(locationMapper.mapToLocationEntity(location));
        switch (databaseResponse) {
            case DatabaseResponse.Success<LocationEntity> success -> {
                return Optional.of(locationMapper.mapToLocation(success.data()));
            }

            case DatabaseResponse.Error<LocationEntity> error -> {
                return Optional.empty();
            }
        }
    }
}
