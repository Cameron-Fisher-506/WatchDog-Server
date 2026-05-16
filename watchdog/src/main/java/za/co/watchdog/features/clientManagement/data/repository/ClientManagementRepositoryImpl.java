package za.co.watchdog.features.clientManagement.data.repository;

import org.springframework.stereotype.Component;
import za.co.watchdog.common.data.local.common.DatabaseResponse;
import za.co.watchdog.common.data.local.database.model.*;
import za.co.watchdog.common.data.local.mapper.UserMapper;
import za.co.watchdog.common.domain.model.User;
import za.co.watchdog.features.clientManagement.data.local.dataSource.ClientManagementLocalDataSource;
import za.co.watchdog.features.clientManagement.data.local.mapper.ClientManagementMapper;
import za.co.watchdog.features.clientManagement.domain.model.Address;
import za.co.watchdog.features.clientManagement.domain.model.Client;
import za.co.watchdog.features.clientManagement.domain.model.Vehicle;
import za.co.watchdog.features.clientManagement.domain.repository.ClientManagementRepository;

import java.util.Optional;

@Component
public class ClientManagementRepositoryImpl implements ClientManagementRepository {
    private final ClientManagementLocalDataSource clientManagementLocalDataSource;
    private final UserMapper userMapper;
    private final ClientManagementMapper clientManagementMapper;

    ClientManagementRepositoryImpl(ClientManagementLocalDataSource clientManagementLocalDataSource, UserMapper userMapper, ClientMapper clientMapper, ClientManagementMapper clientManagementMapper) {
        this.clientManagementLocalDataSource = clientManagementLocalDataSource;
        this.userMapper = userMapper;
        this.clientManagementMapper = clientManagementMapper;
    }

    @Override
    public Optional<Client> saveClient(Client client) {
        Optional<ClientEntity> optional = this.clientManagementLocalDataSource.saveClientEntity(clientMapper.mapToClientEntity(client));
        return optional.map(clientManagementMapper::mapToClient);
    }

    @Override
    public Optional<Vehicle> fetchVehicleByZoneId(Long zoneId) {
        Optional<VehicleEntity> optional = clientManagementLocalDataSource.fetchVehicleByZoneId(zoneId);
        return optional.map(clientManagementMapper::mapToVehicle);
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
    public Optional<Address> saveAddress(Address address) {
        Optional<AddressEntity> optional = clientManagementLocalDataSource.saveAddressEntity(clientManagementMapper.mapToAddressEntity(address));
        return optional.map(clientManagementMapper::mapToAddress);
    }
}
