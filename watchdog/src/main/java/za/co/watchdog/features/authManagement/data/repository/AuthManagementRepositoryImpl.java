package za.co.watchdog.features.authManagement.data.repository;

import org.springframework.stereotype.Component;
import za.co.watchdog.common.data.local.common.DatabaseResponse;
import za.co.watchdog.common.data.local.database.model.DeviceEntity;
import za.co.watchdog.common.data.local.database.model.UserEntity;
import za.co.watchdog.common.data.local.mapper.DeviceMapper;
import za.co.watchdog.common.domain.model.Device;
import za.co.watchdog.common.domain.model.User;
import za.co.watchdog.features.authManagement.data.local.dataSource.AuthManagementLocalDataSource;
import za.co.watchdog.common.data.local.mapper.UserMapper;
import za.co.watchdog.features.authManagement.domain.repository.AuthManagementRepository;

import javax.xml.crypto.Data;
import java.util.Optional;

@Component
public class AuthManagementRepositoryImpl implements AuthManagementRepository {
    private final AuthManagementLocalDataSource authManagementLocalDataSource;
    private final UserMapper userMapper;
    private final DeviceMapper deviceMapper;

    public AuthManagementRepositoryImpl(AuthManagementLocalDataSource authManagementLocalDataSource, UserMapper userMapper, DeviceMapper deviceMapper) {
        this.authManagementLocalDataSource = authManagementLocalDataSource;
        this.userMapper = userMapper;
        this.deviceMapper = deviceMapper;
    }

    @Override
    public Optional<User> fetchUserByEmailAddress(String emailAddress) {
        DatabaseResponse<UserEntity> databaseResponse = this.authManagementLocalDataSource.fetchUserByEmailAddress(emailAddress);
        switch (databaseResponse) {
            case DatabaseResponse.Success<UserEntity> success -> {
                return Optional.of(userMapper.mapToUser(success.data()));
            }

            case DatabaseResponse.Error<UserEntity> error -> {
                return Optional.empty();
            }
        }
    }

    @Override
    public Optional<Device> fetchDeviceByDeviceFingerprint(String deviceFingerprint) {
        DatabaseResponse<DeviceEntity> databaseResponse = this.authManagementLocalDataSource.fetchDeviceByDeviceFingerprint(deviceFingerprint);
        switch (databaseResponse) {
            case DatabaseResponse.Success<DeviceEntity> success -> {
                return Optional.of(deviceMapper.mapToDevice(success.data()));
            }

            case DatabaseResponse.Error<DeviceEntity> error -> {
                return Optional.empty();
            }
        }
    }

    @Override
    public Optional<User> fetchUserById(Long userId) {
        DatabaseResponse<UserEntity> databaseResponse = this.authManagementLocalDataSource.fetchUserById(userId);
        switch (databaseResponse) {
            case DatabaseResponse.Success<UserEntity> success -> {
                return Optional.of(userMapper.mapToUser(success.data()));
            }

            case DatabaseResponse.Error<UserEntity> error -> {
                return Optional.empty();
            }
        }
    }

    @Override
    public Optional<User> register(User user) {
        DatabaseResponse<UserEntity> databaseResponse = this.authManagementLocalDataSource.upsert(userMapper.mapToUserEntity(user));
        switch (databaseResponse) {
            case DatabaseResponse.Success<UserEntity> success -> {
                return Optional.of(userMapper.mapToUser(success.data()));
            }

            case DatabaseResponse.Error<UserEntity> error -> {
                return Optional.empty();
            }
        }
    }

    @Override
    public Optional<Boolean> sendOtp(User user) {
        DatabaseResponse<UserEntity> databaseResponse = this.authManagementLocalDataSource.upsert(userMapper.mapToUserEntity(user));
        switch (databaseResponse) {
            case DatabaseResponse.Success<UserEntity> success -> {
                //TODO: send email to client map result based of service response
                authManagementLocalDataSource.upsert(userMapper.mapToUserEntity(user));
                return Optional.of(true);
            }

            case DatabaseResponse.Error<UserEntity> error -> {
                return Optional.empty();
            }
        }
    }

    @Override
    public Optional<Device> saveUserDevice(Device device) {
        DatabaseResponse<DeviceEntity> databaseResponse = this.authManagementLocalDataSource.upsert(deviceMapper.mapToDeviceEntity(device));
        switch (databaseResponse) {
            case DatabaseResponse.Success<DeviceEntity> success -> {
                return Optional.of(deviceMapper.mapToDevice(success.data()));
            }

            case DatabaseResponse.Error<DeviceEntity> error -> {
                return Optional.empty();
            }
        }
    }
}
