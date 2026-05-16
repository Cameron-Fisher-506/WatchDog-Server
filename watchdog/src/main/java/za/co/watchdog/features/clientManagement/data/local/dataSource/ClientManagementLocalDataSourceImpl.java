package za.co.watchdog.features.clientManagement.data.local.dataSource;

import org.springframework.stereotype.Component;
import za.co.watchdog.common.data.local.common.DatabaseResponse;
import za.co.watchdog.common.data.local.database.dao.*;
import za.co.watchdog.common.data.local.database.model.*;

import java.util.List;
import java.util.Optional;

@Component
public class ClientManagementLocalDataSourceImpl implements ClientManagementLocalDataSource {
    private final ClientDao clientDao;
    private final UserDao userDao;
    private final AddressDao addressDao;
    private final VehicleDao vehicleDao;

    ClientManagementLocalDataSourceImpl(ClientDao clientDao, UserDao userDao, AddressDao addressDao, VehicleDao vehicleDao) {
        this.clientDao = clientDao;
        this.userDao = userDao;
        this.addressDao = addressDao;
        this.vehicleDao = vehicleDao;
    }

    @Override
    public Optional<ClientEntity> saveClientEntity(ClientEntity clientEntity) {
        try {
            return Optional.of(clientDao.save(clientEntity));
        } catch(Exception e) {
            return Optional.empty();
        }
    }

    @Override
    public Optional<AddressEntity> saveAddressEntity(AddressEntity addressEntity) {
        try {
            return Optional.of(addressDao.save(addressEntity));
        } catch(Exception e) {
            return Optional.empty();
        }
    }

    @Override
    public Optional<VehicleEntity> fetchVehicleByZoneId(Long zoneId) {
        try {
            return Optional.of(vehicleDao.findByZoneId(zoneId));
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    @Override
    public DatabaseResponse<UserEntity> fetchUserById(Long userId) {
        try {
            return userDao.findById(userId)
                    .map(DatabaseResponse::success)
                    .orElseGet(() -> DatabaseResponse.error("Account does not exists."));
        } catch(Exception e) {
            return new DatabaseResponse.Error<>(e.getMessage());
        }
    }
}
