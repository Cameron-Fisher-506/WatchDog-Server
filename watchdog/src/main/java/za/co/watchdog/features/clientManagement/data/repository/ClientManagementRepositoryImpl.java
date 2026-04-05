package za.co.watchdog.features.clientManagement.data.repository;

import za.co.watchdog.common.data.local.common.DatabaseResponse;
import za.co.watchdog.common.data.local.mapper.UserMapper;
import za.co.watchdog.common.domain.common.Result;
import za.co.watchdog.features.clientManagement.data.local.dataSource.ClientManagementLocalDataSource;
import za.co.watchdog.features.clientManagement.data.local.mapper.ClientMapper;
import za.co.watchdog.features.clientManagement
import za.co.watchdog.features.clientManagement.domain.model.Client;
import za.co.watchdog.features.clientManagement.domain.repository.ClientManagementRepository;

public class ClientManagementRepositoryImpl implements ClientManagementRepository {
    private ClientManagementLocalDataSource clientManagementLocalDataSource;
    private UserMapper userMapper;
    private ClientMapper clientMapper;

    ClientManagementRepositoryImpl(ClientManagementLocalDataSource clientManagementLocalDataSource, UserMapper userMapper, ClientMapper clientMapper) {
        this.clientManagementLocalDataSource = clientManagementLocalDataSource;
        this.userMapper = userMapper;
        this.clientMapper = clientMapper;
    }

    @Override
    public Result<Client> onboard(Client client) {
        DatabaseResponse<User> databaseResponse = this.clientManagementLocalDataSource.onboard(clientMapper.mapToClientEntity(client));
        switch (databaseResponse) {
            case DatabaseResponse.Success<User> success -> {
                return Result.success(success.data());
            }

            case DatabaseResponse.Error<User> error -> {
                return Result.error(error.message());
            }
        }
    }

    @Override
    public Result<User> fetchUser(User user) {
        DatabaseResponse<User> databaseResponse = clientManagementLocalDataSource.fetchUser(userMapper.mapToUserEntity(user));
        switch (databaseResponse) {
            case DatabaseResponse.Success<User> success -> {
                return Result.success(success.data());
            }

            case DatabaseResponse.Error<User> error -> {
                return Result.error(error.message());
            }
        }
    }
}
