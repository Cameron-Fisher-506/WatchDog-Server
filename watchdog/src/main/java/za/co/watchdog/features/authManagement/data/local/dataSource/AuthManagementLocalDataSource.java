package za.co.watchdog.features.authManagement.data.local.dataSource;

import za.co.watchdog.common.data.local.common.DatabaseResponse;
import za.co.watchdog.common.data.local.database.model.UserEntity;

public interface AuthManagementLocalDataSource {
    public DatabaseResponse<UserEntity> fetchUser(UserEntity userEntity);
    public DatabaseResponse<UserEntity> register(UserEntity userEntity);
    public DatabaseResponse<UserEntity> login(UserEntity userEntity);
}
