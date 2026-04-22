package za.co.watchdog.common.data.local.dataSource;

import za.co.watchdog.common.data.local.common.DatabaseResponse;
import za.co.watchdog.common.data.local.database.model.UserEntity;

public interface UserManagementLocalDataSource {
    public DatabaseResponse<UserEntity> fetchUserByUsername(String username);
}
