package za.co.watchdog.features.clientManagement.domain.repository;

import za.co.watchdog.common.domain.model.User;
import za.co.watchdog.features.clientManagement.domain.model.Client;
import za.co.watchdog.features.clientManagement.domain.model.Location;

import java.util.Optional;

public interface ClientManagementRepository {
    public Optional<User> fetchUserById(Long userId);
    public Optional<Location> saveLocation(Location location);
    public Optional<Client> onboard(Client client);
}
