package za.co.watchdog.features.clientManagement.data.local.dataSource;

import org.springframework.stereotype.Component;
import za.co.watchdog.common.data.local.common.DatabaseResponse;
import za.co.watchdog.common.data.local.database.dao.ClientDao;
import za.co.watchdog.common.data.local.database.model.ClientEntity;
import za.co.watchdog.common.data.local.mapper.ClientMapper;
import za.co.watchdog.features.clientManagement.domain.model.Client;

@Component
public class ClientManagementLocalDataSourceImpl implements ClientManagementLocalDataSource {
    private final ClientDao clientDao;
    private final ClientMapper clientMapper;

    public ClientManagementLocalDataSourceImpl(ClientDao clientDao, ClientMapper clientMapper) {
        this.clientDao = clientDao;
        this.clientMapper = clientMapper;
    }

    @Override
    public DatabaseResponse<Client> fetchClient(ClientEntity clientEntity) {
        try {
            return this.clientDao.findByEmailAddress(clientEntity.getEmailAddress())
                    .map(object -> DatabaseResponse.success(clientMapper.mapToClient(object)))
                    .orElseGet(() -> DatabaseResponse.error("Account does not exists."));
        } catch(Exception e) {
            return new DatabaseResponse.Error<>(e.getMessage());
        }
    }

    @Override
    public DatabaseResponse<Client> registerClient(ClientEntity clientEntity) {
        try {
            return DatabaseResponse.success(clientMapper.mapToClient(this.clientDao.save(clientEntity)));
        } catch(Exception e) {
            return new DatabaseResponse.Error<>(e.getMessage());
        }
    }
}
