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
    public DatabaseResponse<UserEntity> fetchUserByEmailAddress(String emailAddress) {
        try {
            return userDao.findByEmailAddress(emailAddress)
                    .map(DatabaseResponse::success)
                    .orElseGet(() -> DatabaseResponse.error("User does not exists."));
        } catch(Exception e) {
            return new DatabaseResponse.Error<>(e.getMessage());
        }
    }

    @Override
    public DatabaseResponse<UserEntity> fetchUserById(Long userId) {
        try {
            return userDao.findById(userId)
                    .map(DatabaseResponse::success)
                    .orElseGet(() -> DatabaseResponse.error("User does not exists."));
        } catch (Exception e) {
            return new DatabaseResponse.Error<>(e.getMessage());
        }
    }

    @Override
    public DatabaseResponse<UserEntity> upsert(UserEntity userEntity) {
        try {
            return DatabaseResponse.success(this.userDao.save(userEntity));
        } catch (Exception e) {
            return new DatabaseResponse.Error<>(e.getMessage());
        }
    }
}
