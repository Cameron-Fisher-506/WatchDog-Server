package za.co.watchdog.features.incidentManagement.data.local.dataSource;

import org.springframework.stereotype.Component;
import za.co.watchdog.common.data.local.common.DatabaseResponse;
import za.co.watchdog.common.data.local.database.dao.*;
import za.co.watchdog.common.data.local.database.model.*;
import za.co.watchdog.common.domain.model.VehicleStatus;

import java.util.List;
import java.util.Optional;

@Component
public class IncidentManagementLocalDataSourceImpl implements IncidentManagementLocalDataSource {
    private final IncidentDao incidentDao;
    private final AddressDao addressDao;
    private final ClientDao clientDao;
    private final PatrolVehicleDao patrolVehicleDao;

    IncidentManagementLocalDataSourceImpl(IncidentDao incidentDao, AddressDao addressDao, ClientDao clientDao, PatrolVehicleDao patrolVehicleDao) {
        this.incidentDao = incidentDao;
        this.addressDao = addressDao;
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
    public Optional<AddressEntity> fetchAddressById(Long addressId) {
        try {
            return addressDao.findById(addressId);
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    @Override
    public Optional<List<IncidentEntity>> fetchActiveIncidentsBySecurityCompanyId(Long securityCompanyId) {
        try {
            return incidentDao.findAllBySecurityCompanyId(securityCompanyId);
        } catch (Exception e) {
            return Optional.empty();
        }
    }
}
