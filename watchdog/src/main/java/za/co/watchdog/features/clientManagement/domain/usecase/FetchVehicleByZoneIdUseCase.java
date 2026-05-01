package za.co.watchdog.features.clientManagement.domain.usecase;

import org.springframework.stereotype.Service;
import za.co.watchdog.common.domain.exception.ResourceNotFoundException;
import za.co.watchdog.common.domain.usecase.UseCase;
import za.co.watchdog.features.clientManagement.domain.model.Vehicle;
import za.co.watchdog.features.clientManagement.domain.repository.ClientManagementRepository;

@Service
public class FetchVehicleByZoneIdUseCase implements UseCase<Long, Vehicle> {
    private final ClientManagementRepository clientManagementRepository;

    FetchVehicleByZoneIdUseCase(ClientManagementRepository clientManagementRepository) {
        this.clientManagementRepository = clientManagementRepository;
    }

    @Override
    public Vehicle execute(Long input) {
        return clientManagementRepository.fetchVehicleByZoneId(input).orElseThrow(() -> new ResourceNotFoundException("Vehicle", "zoneId", input));
    }
}
