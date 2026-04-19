package za.co.watchdog.features.authManagement.data.local.dataSource;

import org.springframework.stereotype.Component;
import za.co.watchdog.common.data.local.common.DatabaseResponse;
import za.co.watchdog.common.data.local.database.dao.DeviceDao;
import za.co.watchdog.common.data.local.database.dao.UserDao;
import za.co.watchdog.common.data.local.database.model.DeviceEntity;
import za.co.watchdog.common.data.local.database.model.UserEntity;

@Component
public class AuthManagementLocalDataSourceImpl implements AuthManagementLocalDataSource {
    private final UserDao userDao;
    private final DeviceDao deviceDao;

    public AuthManagementLocalDataSourceImpl(UserDao userDao, DeviceDao deviceDao) {
        this.userDao = userDao;
        this.deviceDao = deviceDao;
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
                    .orElseGet(() -> DatabaseResponse.error("User does not exist."));
        } catch (Exception e) {
            return new DatabaseResponse.Error<>(e.getMessage());
        }
    }

    @Override
    public DatabaseResponse<DeviceEntity> fetchDeviceByDeviceFingerprint(String deviceFingerprint) {
        try {
            return deviceDao.findByDeviceFingerprint(deviceFingerprint)
                    .map(DatabaseResponse::success)
                    .orElseGet(() -> DatabaseResponse.error("Device does not exist."));
        } catch (Exception e) {
            return new DatabaseResponse.Error<>(e.getMessage());
        }
    }

    @Override
    public DatabaseResponse<DeviceEntity> fetchDeviceByUserId(Long userId) {
        try {
            return deviceDao.findByUserId(userId)
                    .map(DatabaseResponse::success)
                    .orElseGet(() -> DatabaseResponse.error("Device does not exist."));
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

    @Override
    public DatabaseResponse<DeviceEntity> upsert(DeviceEntity deviceEntity) {
        try {
            return DatabaseResponse.success(this.deviceDao.save(deviceEntity));
        } catch (Exception e) {
            return new DatabaseResponse.Error<>(e.getMessage());
        }
    }
}
