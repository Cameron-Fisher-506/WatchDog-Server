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

    public ClientManagementLocalDataSourceImpl(ClientDao clientDao) {
        this.clientDao = clientDao;
    }

    @Override
    public DatabaseResponse<Client> fetchClient(ClientEntity clientEntity) {
        try {
            return this.clientDao.findByEmailAddress(clientEntity.getEmailAddress())
                    .map(object -> DatabaseResponse.success(ClientMapper.mapToClient(object)))
                    .orElseGet(() -> DatabaseResponse.error("Account does not exists."));
        } catch(Exception e) {
            return new DatabaseResponse.Error<>(e.getMessage());
        }
    }

    @Override
    public DatabaseResponse<Client> registerClient(ClientEntity clientEntity) {
        try {
            return DatabaseResponse.success(ClientMapper.mapToClient(this.clientDao.save(clientEntity)));
        } catch(Exception e) {
            return new DatabaseResponse.Error<>(e.getMessage());
        }
    }
}
