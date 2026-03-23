package za.co.watchdog.features.clientManagement.data.repository;

import org.springframework.stereotype.Component;
import za.co.watchdog.common.data.local.common.DatabaseResponse;
import za.co.watchdog.common.data.local.mapper.ClientMapper;
import za.co.watchdog.common.domain.common.Result;
import za.co.watchdog.features.clientManagement.data.local.dataSource.ClientManagementLocalDataSource;
import za.co.watchdog.features.clientManagement.domain.model.Client;
import za.co.watchdog.features.clientManagement.domain.repository.ClientManagementRepository;

@Component
public class ClientManagementRepositoryImpl implements ClientManagementRepository {
    private final ClientManagementLocalDataSource clientManagementLocalDataSource;

    public ClientManagementRepositoryImpl(ClientManagementLocalDataSource clientManagementLocalDataSource) {
        this.clientManagementLocalDataSource = clientManagementLocalDataSource;
    }


    @Override
    public Result<Client> fetchClient(Client client) {
        DatabaseResponse<Client> databaseResponse = this.clientManagementLocalDataSource.fetchClient(ClientMapper.mapToClientEntity(client));
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
        DatabaseResponse<Client> databaseResponse = this.clientManagementLocalDataSource.registerClient(ClientMapper.mapToClientEntity(client));
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
