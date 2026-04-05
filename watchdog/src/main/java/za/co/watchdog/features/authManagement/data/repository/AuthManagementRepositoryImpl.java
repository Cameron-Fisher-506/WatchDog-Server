package za.co.watchdog.features.authManagement.data.repository;

import org.jspecify.annotations.NonNull;
import org.springframework.stereotype.Component;
import za.co.watchdog.common.data.local.common.DatabaseResponse;
import za.co.watchdog.common.data.local.database.model.UserEntity;
import za.co.watchdog.common.domain.common.Result;
import za.co.watchdog.common.domain.model.User;
import za.co.watchdog.features.authManagement.data.local.dataSource.AuthManagementLocalDataSource;
import za.co.watchdog.common.data.local.mapper.UserMapper;
import za.co.watchdog.features.authManagement.domain.repository.AuthManagementRepository;

@Component
public class AuthManagementRepositoryImpl implements AuthManagementRepository {
    private final AuthManagementLocalDataSource authManagementLocalDataSource;
    private final UserMapper userMapper;

    public AuthManagementRepositoryImpl(AuthManagementLocalDataSource authManagementLocalDataSource, UserMapper userMapper) {
        this.authManagementLocalDataSource = authManagementLocalDataSource;
        this.userMapper = userMapper;
    }


    @Override
    public Result<User> fetchUser(User user) {
        DatabaseResponse<UserEntity> databaseResponse = this.authManagementLocalDataSource.fetchUser(userMapper.mapToUserEntity(user));
        return getUserResult(databaseResponse);
    }

    @NonNull
    public Result<User> getUserResult(DatabaseResponse<UserEntity> databaseResponse) {
        return getUserResult(databaseResponse, userMapper);
    }

    @Override
    public Result<User> register(User user) {
        DatabaseResponse<UserEntity> databaseResponse = this.authManagementLocalDataSource.register(userMapper.mapToUserEntity(user));
        return getUserResult(databaseResponse);
    }
}
