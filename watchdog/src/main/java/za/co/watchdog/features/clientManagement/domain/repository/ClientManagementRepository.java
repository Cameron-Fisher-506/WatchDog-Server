package za.co.watchdog.features.clientManagement.domain.repository;

import za.co.watchdog.common.domain.common.Result;
import za.co.watchdog.common.domain.model.User;
import za.co.watchdog.common.domain.model.Client;

public interface ClientManagementRepository {
    public Result<User> fetchUser(User user);
    public Result<Client> onboard(Client client);
}
