package za.co.watchdog.features.clientManagement.domain.repository;

import za.co.watchdog.common.domain.common.Result;
import za.co.watchdog.features.clientManagement.domain.model.Client;

public interface ClientManagementRepository {
    public Result<Client> fetchClient(Client client);
    public Result<Client> registerClient(Client client);
}
