package za.co.watchdog.features.incidentManagement.data.repository;

import org.springframework.stereotype.Component;
import za.co.watchdog.common.data.local.common.DatabaseResponse;
import za.co.watchdog.common.data.local.database.model.ClientEntity;
import za.co.watchdog.common.data.local.database.model.IncidentEntity;
import za.co.watchdog.common.data.local.database.model.LocationEntity;
import za.co.watchdog.common.data.local.database.model.PatrolVehicleEntity;
import za.co.watchdog.common.domain.model.VehicleStatus;
import za.co.watchdog.features.incidentManagement.data.local.dataSource.IncidentManagementLocalDataSource;
import za.co.watchdog.features.incidentManagement.data.local.mapper.IncidentManagementClientMapper;
import za.co.watchdog.features.incidentManagement.data.local.mapper.IncidentManagementIncidentMapper;
import za.co.watchdog.features.clientManagement.data.local.mapper.ClientManagementLocationMapper;
import za.co.watchdog.features.incidentManagement.data.local.mapper.IncidentManagementPatrolVehicleMapper;
import za.co.watchdog.features.incidentManagement.domain.model.Client;
import za.co.watchdog.features.incidentManagement.domain.model.Incident;
import za.co.watchdog.features.incidentManagement.domain.model.Location;
import za.co.watchdog.features.incidentManagement.domain.model.PatrolVehicle;
import za.co.watchdog.features.incidentManagement.domain.repository.IncidentManagementRepository;

import java.util.Optional;

@Component
public class IncidentManagementRepositoryImpl implements IncidentManagementRepository {
    private final IncidentManagementLocalDataSource incidentManagementLocalDataSource;
    private final IncidentManagementIncidentMapper incidentManagementIncidentMapper;
    private final ClientManagementLocationMapper clientManagementLocationMapper;
    private final IncidentManagementClientMapper incidentManagementClientMapper;
    private final IncidentManagementPatrolVehicleMapper incidentManagementPatrolVehicleMapper;

    IncidentManagementRepositoryImpl(
            IncidentManagementLocalDataSource incidentManagementLocalDataSource,
            IncidentManagementIncidentMapper incidentManagementIncidentMapper,
            ClientManagementLocationMapper clientManagementLocationMapper,
            IncidentManagementClientMapper incidentManagementClientMapper,
            IncidentManagementPatrolVehicleMapper incidentManagementPatrolVehicleMapper
    ) {
        this.incidentManagementLocalDataSource = incidentManagementLocalDataSource;
        this.incidentManagementIncidentMapper = incidentManagementIncidentMapper;
        this.clientManagementLocationMapper = clientManagementLocationMapper;
        this.incidentManagementClientMapper = incidentManagementClientMapper;
        this.incidentManagementPatrolVehicleMapper = incidentManagementPatrolVehicleMapper;
    }

    @Override
    public Optional<Incident> saveIncident(Incident incident) {
        DatabaseResponse<IncidentEntity> databaseResponse = incidentManagementLocalDataSource.saveIncidentEntity(incidentManagementIncidentMapper.mapToIncidentEntity(incident));
        switch (databaseResponse) {
            case DatabaseResponse.Success<IncidentEntity> success -> {
                return Optional.of(incidentManagementIncidentMapper.mapToIncident(success.data()));
            }

            case DatabaseResponse.Error<IncidentEntity> error -> {
                return Optional.empty();
            }
        }
    }

    @Override
    public Optional<Client> fetchClientBuUserId(Long userId) {
        DatabaseResponse<ClientEntity> databaseResponse = incidentManagementLocalDataSource.fetchClientByUserId(userId);
        switch (databaseResponse) {
            case DatabaseResponse.Success<ClientEntity> success -> {
                return Optional.of(incidentManagementClientMapper.mapToClient(success.data()));
            }

            case DatabaseResponse.Error<ClientEntity> error -> {
                return Optional.empty();
            }
        }
    }

    @Override
    public Optional<PatrolVehicle> fetchPatrolVehicleByZoneIdAndVehicleStatus(Long zoneId, VehicleStatus vehicleStatus) {
        Optional<PatrolVehicleEntity> optional = incidentManagementLocalDataSource.fetchPatrolVehicleByZoneIdAndVehicleStatus(zoneId, vehicleStatus);
        return optional.map(incidentManagementPatrolVehicleMapper::mapToPatrolVehicle);
    }

    @Override
    public Optional<Location> fetchLocationById(Long locationId) {
        Optional<LocationEntity> optional = incidentManagementLocalDataSource.fetchLocationById(locationId);
        return optional.map(clientManagementLocationMapper::mapToLocation);
    }
}
