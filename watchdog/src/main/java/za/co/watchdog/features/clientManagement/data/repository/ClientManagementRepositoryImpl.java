package za.co.watchdog.features.clientManagement.data.repository;

import org.springframework.stereotype.Component;
import za.co.watchdog.common.data.local.common.DatabaseResponse;
import za.co.watchdog.common.data.local.database.model.ClientEntity;
import za.co.watchdog.common.data.local.database.model.LocationEntity;
import za.co.watchdog.common.data.local.database.model.UserEntity;
import za.co.watchdog.common.data.local.mapper.UserMapper;
import za.co.watchdog.features.clientManagement.data.local.mapper.ClientManagementLocationMapper;
import za.co.watchdog.features.incidentManagement.domain.model.Client;
import za.co.watchdog.common.domain.model.User;
import za.co.watchdog.features.clientManagement.data.local.dataSource.ClientManagementLocalDataSource;
import za.co.watchdog.features.clientManagement.data.local.mapper.ClientMapper;
import za.co.watchdog.features.clientManagement.domain.repository.ClientManagementRepository;
import za.co.watchdog.features.incidentManagement.domain.model.Location;

import java.util.Optional;

@Component
public class ClientManagementRepositoryImpl implements ClientManagementRepository {
    private final ClientManagementLocalDataSource clientManagementLocalDataSource;
    private final UserMapper userMapper;
    private final ClientMapper clientMapper;
    private final ClientManagementLocationMapper clientManagementLocationMapper;

    ClientManagementRepositoryImpl(ClientManagementLocalDataSource clientManagementLocalDataSource, UserMapper userMapper, ClientMapper clientMapper, ClientManagementLocationMapper clientManagementLocationMapper) {
        this.clientManagementLocalDataSource = clientManagementLocalDataSource;
        this.userMapper = userMapper;
        this.clientMapper = clientMapper;
        this.clientManagementLocationMapper = clientManagementLocationMapper;
    }

    @Override
    public Optional<Client> onboard(Client client) {
        DatabaseResponse<ClientEntity> databaseResponse = this.clientManagementLocalDataSource.onboard(clientMapper.mapToClientEntity(client));
        switch (databaseResponse) {
            case DatabaseResponse.Success<ClientEntity> success -> {
                return Optional.of(clientMapper.mapToClient(success.data()));
            }

            case DatabaseResponse.Error<ClientEntity> error -> {
                return Optional.empty();
            }
        }
    }

    @Override
    public Optional<User> fetchUserById(Long userId) {
        DatabaseResponse<UserEntity> databaseResponse = clientManagementLocalDataSource.fetchUserById(userId);
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
    public Optional<Location> saveLocation(Location location) {
        DatabaseResponse<LocationEntity> databaseResponse = clientManagementLocalDataSource.saveLocationEntity(clientManagementLocationMapper.mapToLocationEntity(location));
        switch (databaseResponse) {
            case DatabaseResponse.Success<LocationEntity> success -> {
                return Optional.of(clientManagementLocationMapper.mapToLocation(success.data()));
            }

            case DatabaseResponse.Error<LocationEntity> error -> {
                return Optional.empty();
            }
        }
    }

    @Override
    public Optional<User> saveUser(User user) {
        DatabaseResponse<UserEntity> databaseResponse = clientManagementLocalDataSource.saveUserEntity(userMapper.mapToUserEntity(user));
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
