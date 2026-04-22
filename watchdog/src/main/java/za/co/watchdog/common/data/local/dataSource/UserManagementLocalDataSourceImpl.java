package za.co.watchdog.common.data.local.dataSource;

import org.springframework.stereotype.Component;
import za.co.watchdog.common.data.local.common.DatabaseResponse;
import za.co.watchdog.common.data.local.database.dao.UserDao;
import za.co.watchdog.common.data.local.database.model.UserEntity;

@Component
public class UserManagementLocalDataSourceImpl implements UserManagementLocalDataSource {
    private final UserDao userDao;

    UserManagementLocalDataSourceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public DatabaseResponse<UserEntity> fetchUserByUsername(String username) {
        try {
            return userDao.findByEmailAddress(username)
                    .map(DatabaseResponse::success)
                    .orElseGet(() -> DatabaseResponse.error("User not found"));
        } catch(Exception e) {
            return new DatabaseResponse.Error<>(e.getMessage());
        }
    }
}
