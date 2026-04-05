package za.co.watchdog.features.authManagement.data.local.dataSource;

import org.springframework.stereotype.Component;
import za.co.watchdog.common.data.local.common.DatabaseResponse;
import za.co.watchdog.common.data.local.database.dao.UserDao;
import za.co.watchdog.common.data.local.database.model.UserEntity;
import za.co.watchdog.common.data.local.mapper.UserMapper;

@Component
public class AuthManagementLocalDataSourceImpl implements AuthManagementLocalDataSource {
    private final UserDao userDao;
    private final UserMapper userMapper;

    public AuthManagementLocalDataSourceImpl(UserDao userDao, UserMapper userMapper) {
        this.userDao = userDao;
        this.userMapper = userMapper;
    }

    @Override
    public DatabaseResponse<User> fetchUser(UserEntity userEntity) {
        try {
            return userDao.findByEmailAddress(userEntity.getEmailAddress())
                    .map(object -> DatabaseResponse.success(userMapper.mapToUser(object)))
                    .orElseGet(() -> DatabaseResponse.error("Account does not exists."));
        } catch(Exception e) {
            return new DatabaseResponse.Error<>(e.getMessage());
        }
    }

    @Override
    public DatabaseResponse<User> register(UserEntity userEntity) {
        try {
            return DatabaseResponse.success(userMapper.mapToUser(this.userDao.save(userEntity)));
        } catch(Exception e) {
            return new DatabaseResponse.Error<>(e.getMessage());
        }
    }

    @Override
    public DatabaseResponse<User> login(UserEntity userEntity) {
        return null;
    }
}
