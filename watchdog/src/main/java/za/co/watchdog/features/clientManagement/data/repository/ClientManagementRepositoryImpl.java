package za.co.watchdog.features.clientManagement.data.repository;

import org.springframework.stereotype.Component;
import za.co.watchdog.common.data.local.common.DatabaseResponse;
import za.co.watchdog.common.data.local.database.model.ClientEntity;
import za.co.watchdog.common.data.local.database.model.UserEntity;
import za.co.watchdog.common.data.local.mapper.UserMapper;
import za.co.watchdog.common.domain.common.Result;
import za.co.watchdog.common.domain.model.Client;
import za.co.watchdog.common.domain.model.User;
import za.co.watchdog.features.clientManagement.data.local.dataSource.ClientManagementLocalDataSource;
import za.co.watchdog.features.clientManagement.data.local.mapper.ClientMapper;
import za.co.watchdog.features.clientManagement.domain.repository.ClientManagementRepository;

@Component
public class ClientManagementRepositoryImpl implements ClientManagementRepository {
    private final ClientManagementLocalDataSource clientManagementLocalDataSource;
    private final UserMapper userMapper;
    private final ClientMapper clientMapper;

    ClientManagementRepositoryImpl(ClientManagementLocalDataSource clientManagementLocalDataSource, UserMapper userMapper, ClientMapper clientMapper) {
        this.clientManagementLocalDataSource = clientManagementLocalDataSource;
        this.userMapper = userMapper;
        this.clientMapper = clientMapper;
    }

    @Override
    public Result<Client> onboard(Client client) {
        DatabaseResponse<ClientEntity> databaseResponse = this.clientManagementLocalDataSource.onboard(clientMapper.mapToClientEntity(client));
        switch (databaseResponse) {
            case DatabaseResponse.Success<ClientEntity> success -> {
                return Result.success(clientMapper.mapToClient(success.data()));
            }

            case DatabaseResponse.Error<ClientEntity> error -> {
                return Result.error(error.message());
            }
        }
    }

    @Override
    public Result<User> fetchUser(User user) {
        DatabaseResponse<UserEntity> databaseResponse = clientManagementLocalDataSource.fetchUser(userMapper.mapToUserEntity(user));
        switch (databaseResponse) {
            case DatabaseResponse.Success<UserEntity> success -> {
                return Result.success(userMapper.mapToUser(success.data()));
            }

            case DatabaseResponse.Error<UserEntity> error -> {
                return Result.error(error.message());
            }
        }
    }
}
