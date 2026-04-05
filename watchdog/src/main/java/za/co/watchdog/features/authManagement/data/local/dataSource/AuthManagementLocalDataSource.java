package za.co.watchdog.features.authManagement.data.local.dataSource;

import za.co.watchdog.common.data.local.common.DatabaseResponse;
import za.co.watchdog.common.data.local.database.model.UserEntity;
import za.co.watchdog.common.domain.model.User;

public interface AuthManagementLocalDataSource {
    public DatabaseResponse<User> fetchUser(UserEntity userEntity);
    public DatabaseResponse<User> register(UserEntity userEntity);
    public DatabaseResponse<User> login(UserEntity userEntity);
}
