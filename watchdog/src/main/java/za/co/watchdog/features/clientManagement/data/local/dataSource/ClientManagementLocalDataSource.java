package za.co.watchdog.features.clientManagement.data.local.dataSource;

import za.co.watchdog.common.data.local.common.DatabaseResponse;
import za.co.watchdog.common.data.local.database.model.ClientEntity;
import za.co.watchdog.common.data.local.database.model.LocationEntity;
import za.co.watchdog.common.data.local.database.model.UserEntity;
import za.co.watchdog.common.data.local.database.model.VehicleEntity;

import javax.xml.crypto.Data;
import java.util.List;
import java.util.Optional;

public interface ClientManagementLocalDataSource {
    public DatabaseResponse<UserEntity> fetchUserById(Long userId);
    public DatabaseResponse<ClientEntity> onboard(ClientEntity clientEntity);
    public DatabaseResponse<LocationEntity> saveLocationEntity(LocationEntity locationEntity);
    public Optional<VehicleEntity> fetchVehicleByZoneId(Long zoneId);
}
