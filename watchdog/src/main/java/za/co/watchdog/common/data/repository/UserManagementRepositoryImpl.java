package za.co.watchdog.common.data.repository;

import org.springframework.stereotype.Component;
import za.co.watchdog.common.data.local.common.DatabaseResponse;
import za.co.watchdog.common.data.local.dataSource.UserManagementLocalDataSource;
import za.co.watchdog.common.data.local.database.model.UserEntity;
import za.co.watchdog.common.data.local.mapper.UserMapper;
import za.co.watchdog.common.domain.model.User;
import za.co.watchdog.common.domain.repository.UserManagementRepository;

import java.util.Optional;

@Component
public class UserManagementRepositoryImpl implements UserManagementRepository {
    private final UserManagementLocalDataSource userManagementLocalDataSource;
    private final UserMapper userMapper;

    UserManagementRepositoryImpl(UserManagementLocalDataSource userManagementLocalDataSource, UserMapper userMapper) {
        this.userManagementLocalDataSource = userManagementLocalDataSource;
        this.userMapper = userMapper;
    }

    @Override
    public Optional<User> fetchUserByUsername(String username) {
        DatabaseResponse<UserEntity> databaseResponse = userManagementLocalDataSource.fetchUserByUsername(username);
        switch (databaseResponse) {
            case DatabaseResponse.Success<UserEntity> success -> {
                return Optional.of(userMapper.mapToUser(success.data()));
            }

            case DatabaseResponse.Error<UserEntity> error -> {
                return Optional.empty();
            }
        }
    }
}
