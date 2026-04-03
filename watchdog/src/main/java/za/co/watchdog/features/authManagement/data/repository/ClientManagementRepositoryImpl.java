package za.co.watchdog.features.authManagement.data.repository;

import org.springframework.stereotype.Component;
import za.co.watchdog.common.data.local.common.DatabaseResponse;
import za.co.watchdog.common.data.local.mapper.ClientMapper;
import za.co.watchdog.common.domain.common.Result;
import za.co.watchdog.features.authManagement.data.local.dataSource.ClientManagementLocalDataSource;
import za.co.watchdog.features.authManagement.domain.model.Client;
import za.co.watchdog.features.authManagement.domain.repository.ClientManagementRepository;

@Component
public class ClientManagementRepositoryImpl implements ClientManagementRepository {
    private final ClientManagementLocalDataSource clientManagementLocalDataSource;
    private final ClientMapper clientMapper;

    public ClientManagementRepositoryImpl(ClientManagementLocalDataSource clientManagementLocalDataSource, ClientMapper clientMapper) {
        this.clientManagementLocalDataSource = clientManagementLocalDataSource;
        this.clientMapper = clientMapper;
    }


    @Override
    public Result<Client> fetchClient(Client client) {
        DatabaseResponse<Client> databaseResponse = this.clientManagementLocalDataSource.fetchClient(clientMapper.mapToClientEntity(client));
        switch (databaseResponse) {
            case DatabaseResponse.Success<Client> success -> {
                return Result.success(success.data());
            }

            case DatabaseResponse.Error<Client> error -> {
                return Result.error(error.message());
            }
        }
    }

    @Override
    public Result<Client> registerClient(Client client) {
        DatabaseResponse<Client> databaseResponse = this.clientManagementLocalDataSource.registerClient(clientMapper.mapToClientEntity(client));
        switch (databaseResponse) {
            case DatabaseResponse.Success<Client> success -> {
                return Result.success(success.data());
            }

            case DatabaseResponse.Error<Client> error -> {
                return Result.error(error.message());
            }
        }
    }
}
