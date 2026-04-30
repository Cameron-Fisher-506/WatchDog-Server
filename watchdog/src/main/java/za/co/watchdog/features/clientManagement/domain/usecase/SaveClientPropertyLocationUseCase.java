package za.co.watchdog.features.clientManagement.domain.usecase;

import org.springframework.stereotype.Service;
import za.co.watchdog.common.domain.exception.ResourceNotFoundException;
import za.co.watchdog.common.domain.usecase.UseCase;
import za.co.watchdog.features.clientManagement.domain.repository.ClientManagementRepository;
import za.co.watchdog.features.clientManagement.domain.model.Location;

@Service
public class SaveClientPropertyLocationUseCase implements UseCase<Location, Location> {
    private final ClientManagementRepository clientManagementRepository;

    SaveClientPropertyLocationUseCase(ClientManagementRepository clientManagementRepository) {
        this.clientManagementRepository = clientManagementRepository;
    }

    @Override
    public Location execute(Location input) {
        return clientManagementRepository.saveLocation(input).orElseThrow(() -> new ResourceNotFoundException("SaveLocation", "location", input));
    }
}
