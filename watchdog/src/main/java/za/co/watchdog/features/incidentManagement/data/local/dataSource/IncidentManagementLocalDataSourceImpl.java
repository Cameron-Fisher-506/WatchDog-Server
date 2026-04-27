package za.co.watchdog.features.incidentManagement.data.local.dataSource;

import org.springframework.stereotype.Component;
import za.co.watchdog.common.data.local.common.DatabaseResponse;
import za.co.watchdog.common.data.local.database.dao.ClientDao;
import za.co.watchdog.common.data.local.database.dao.IncidentDao;
import za.co.watchdog.common.data.local.database.dao.LocationDao;
import za.co.watchdog.common.data.local.database.dao.PatrolVehicleDao;
import za.co.watchdog.common.data.local.database.model.*;
import za.co.watchdog.common.domain.model.VehicleStatus;

import java.util.Optional;

@Component
public class IncidentManagementLocalDataSourceImpl implements IncidentManagementLocalDataSource {
    private final IncidentDao incidentDao;
    private final LocationDao locationDao;
    private final ClientDao clientDao;
    private final PatrolVehicleDao patrolVehicleDao;

    IncidentManagementLocalDataSourceImpl(IncidentDao incidentDao, LocationDao locationDao, ClientDao clientDao, PatrolVehicleDao patrolVehicleDao) {
        this.incidentDao = incidentDao;
        this.locationDao = locationDao;
        this.clientDao = clientDao;
        this.patrolVehicleDao = patrolVehicleDao;
    }

    @Override
    public DatabaseResponse<IncidentEntity> saveIncidentEntity(IncidentEntity incidentEntity) {
        try {
            return DatabaseResponse.success(incidentDao.save(incidentEntity));
        } catch(Exception e) {
            return DatabaseResponse.error(e.getMessage());
        }
    }

    @Override
    public DatabaseResponse<ClientEntity> fetchClientByUserId(Long userId) {
        try {
            return clientDao.findByUserId(userId)
                    .map(DatabaseResponse::success)
                    .orElseGet(() -> DatabaseResponse.error("Client not found."));
        } catch (Exception e) {
            return DatabaseResponse.error(e.getMessage());
        }
    }

    @Override
    public Optional<PatrolVehicleEntity> fetchPatrolVehicleByZoneIdAndVehicleStatus(Long zoneId, VehicleStatus vehicleStatus) {
        try {
            return patrolVehicleDao.findPatrolVehicleByZoneIdAndVehicleStatus(zoneId, vehicleStatus);
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    @Override
    public Optional<LocationEntity> fetchLocationById(Long locationId) {
        try {
            return locationDao.findById(locationId);
        } catch (Exception e) {
            return Optional.empty();
        }
    }
}
