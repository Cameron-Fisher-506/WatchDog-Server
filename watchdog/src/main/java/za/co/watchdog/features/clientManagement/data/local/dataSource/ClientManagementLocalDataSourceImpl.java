package za.co.watchdog.features.clientManagement.data.local.dataSource;

import org.springframework.stereotype.Component;
import za.co.watchdog.common.data.local.common.DatabaseResponse;
import za.co.watchdog.common.data.local.database.dao.ClientDao;
import za.co.watchdog.common.data.local.database.dao.LocationDao;
import za.co.watchdog.common.data.local.database.dao.UserDao;
import za.co.watchdog.common.data.local.database.model.ClientEntity;
import za.co.watchdog.common.data.local.database.model.LocationEntity;
import za.co.watchdog.common.data.local.database.model.UserEntity;

@Component
public class ClientManagementLocalDataSourceImpl implements ClientManagementLocalDataSource {
    private final ClientDao clientDao;
    private final UserDao userDao;
    private final LocationDao locationDao;

    ClientManagementLocalDataSourceImpl(ClientDao clientDao, UserDao userDao, LocationDao locationDao) {
        this.clientDao = clientDao;
        this.userDao = userDao;
        this.locationDao = locationDao;
    }

    @Override
    public DatabaseResponse<ClientEntity> onboard(ClientEntity clientEntity) {
        try {
            return DatabaseResponse.success(clientDao.save(clientEntity));
        } catch(Exception e) {
            return new DatabaseResponse.Error<>(e.getMessage());
        }
    }

    @Override
    public DatabaseResponse<LocationEntity> saveLocationEntity(LocationEntity locationEntity) {
        try {
            return DatabaseResponse.success(locationDao.save(locationEntity));
        } catch(Exception e) {
            return DatabaseResponse.error(e.getMessage());
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
