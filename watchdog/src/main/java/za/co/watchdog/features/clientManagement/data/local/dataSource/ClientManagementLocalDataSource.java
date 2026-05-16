package za.co.watchdog.features.clientManagement.data.local.dataSource;

import za.co.watchdog.common.data.local.common.DatabaseResponse;
import za.co.watchdog.common.data.local.database.model.*;

import java.util.Optional;

public interface ClientManagementLocalDataSource {
    public DatabaseResponse<UserEntity> fetchUserById(Long userId);
    public Optional<AddressEntity> saveAddressEntity(AddressEntity addressEntity);
    public Optional<VehicleEntity> fetchVehicleByZoneId(Long zoneId);
    public Optional<ClientEntity> saveClientEntity(ClientEntity clientEntity);
}
