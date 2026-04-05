package za.co.watchdog.features.clientManagement.data.local.dataSource;

import za.co.watchdog.common.data.local.common.DatabaseResponse;
import za.co.watchdog.common.data.local.database.model.ClientEntity;
import za.co.watchdog.common.data.local.database.model.UserEntity;
import za.co.watchdog.features.clientManagement.domain.model.Client;

public interface ClientManagementLocalDataSource {
    public DatabaseResponse<User> fetchUser(UserEntity userEntity);
    public DatabaseResponse<ClientEntity> onboard(ClientEntity clientEntity);
}
