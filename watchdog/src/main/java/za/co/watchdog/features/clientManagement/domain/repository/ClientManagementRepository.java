package za.co.watchdog.features.clientManagement.domain.repository;

import za.co.watchdog.common.domain.model.User;
import za.co.watchdog.common.domain.model.Client;

import java.util.Optional;

public interface ClientManagementRepository {
    public Optional<User> fetchUserById(Long userId);
    public Optional<User> saveUser(User user);
    public Optional<Client> onboard(Client client);
}
