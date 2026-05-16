package za.co.watchdog.features.clientManagement.domain.repository;

import za.co.watchdog.common.domain.model.User;
import za.co.watchdog.features.clientManagement.domain.model.Address;
import za.co.watchdog.features.clientManagement.domain.model.Client;
import za.co.watchdog.features.clientManagement.domain.model.Vehicle;
import java.util.Optional;

public interface ClientManagementRepository {
    public Optional<User> fetchUserById(Long userId);
    public Optional<Address> saveAddress(Address address);
    public Optional<Client> saveClient(Client client);
    public Optional<Vehicle> fetchVehicleByZoneId(Long zoneId);
}
