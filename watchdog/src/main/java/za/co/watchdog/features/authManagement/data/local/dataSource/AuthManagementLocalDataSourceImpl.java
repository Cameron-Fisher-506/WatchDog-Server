package za.co.watchdog.features.authManagement.data.local.dataSource;

import org.springframework.stereotype.Component;
import za.co.watchdog.common.data.local.common.DatabaseResponse;
import za.co.watchdog.common.data.local.database.dao.UserDao;
import za.co.watchdog.common.data.local.database.model.UserEntity;

@Component
public class AuthManagementLocalDataSourceImpl implements AuthManagementLocalDataSource {
    private final UserDao userDao;

    public AuthManagementLocalDataSourceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public DatabaseResponse<UserEntity> fetchUser(UserEntity userEntity) {
        try {
            return userDao.findByEmailAddress(userEntity.getEmailAddress())
                    .map(DatabaseResponse::success)
                    .orElseGet(() -> DatabaseResponse.error("Account does not exists."));
        } catch(Exception e) {
            return new DatabaseResponse.Error<>(e.getMessage());
        }
    }

    @Override
    public DatabaseResponse<UserEntity> register(UserEntity userEntity) {
        try {
            return DatabaseResponse.success(this.userDao.save(userEntity));
        } catch(Exception e) {
            return new DatabaseResponse.Error<>(e.getMessage());
        }
    }

    @Override
    public DatabaseResponse<UserEntity> login(UserEntity userEntity) {
        return null;
    }
}
