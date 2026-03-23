package za.co.watchdog.features.clientManagement.data.local.dataSource;

import za.co.watchdog.common.data.local.common.DatabaseResponse;
import za.co.watchdog.common.data.local.database.model.ClientEntity;
import za.co.watchdog.features.clientManagement.domain.model.Client;

public interface ClientManagementLocalDataSource {
    public DatabaseResponse<Client> fetchClient(ClientEntity clientEntity);
    public DatabaseResponse<Client> registerClient(ClientEntity clientEntity);
}
