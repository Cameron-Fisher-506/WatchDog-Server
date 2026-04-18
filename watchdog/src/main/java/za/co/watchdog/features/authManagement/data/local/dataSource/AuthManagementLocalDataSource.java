package za.co.watchdog.features.authManagement.data.local.dataSource;

import org.hibernate.boot.model.relational.Database;
import za.co.watchdog.common.data.local.common.DatabaseResponse;
import za.co.watchdog.common.data.local.database.model.DeviceEntity;
import za.co.watchdog.common.data.local.database.model.UserEntity;

public interface AuthManagementLocalDataSource {
    public DatabaseResponse<UserEntity> fetchUserByEmailAddress(String emailAddress);
    public DatabaseResponse<UserEntity> fetchUserById(Long userId);
    public DatabaseResponse<DeviceEntity> fetchDeviceByDeviceFingerprint(String deviceFingerprint);
    public DatabaseResponse<DeviceEntity> fetchDeviceByUserId(Long userId);
    public DatabaseResponse<UserEntity> upsert(UserEntity userEntity);
    public DatabaseResponse<DeviceEntity> upsert(DeviceEntity deviceEntity);
}
