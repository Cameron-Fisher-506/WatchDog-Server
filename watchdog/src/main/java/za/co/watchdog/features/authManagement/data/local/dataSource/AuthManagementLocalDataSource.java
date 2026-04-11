package za.co.watchdog.features.authManagement.data.local.dataSource;

import za.co.watchdog.common.data.local.common.DatabaseResponse;
import za.co.watchdog.common.data.local.database.model.UserEntity;

public interface AuthManagementLocalDataSource {
    public DatabaseResponse<UserEntity> fetchUserByEmailAddress(String emailAddress);
    public DatabaseResponse<UserEntity> fetchUserById(Long userId);
    public DatabaseResponse<UserEntity> upsert(UserEntity userEntity);
}
