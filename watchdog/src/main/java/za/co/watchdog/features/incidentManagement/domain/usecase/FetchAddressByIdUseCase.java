package za.co.watchdog.features.incidentManagement.domain.usecase;

import org.springframework.stereotype.Service;
import za.co.watchdog.common.domain.exception.ResourceNotFoundException;
import za.co.watchdog.common.domain.usecase.UseCase;
import za.co.watchdog.features.incidentManagement.domain.model.Address;
import za.co.watchdog.features.incidentManagement.domain.model.Location;
import za.co.watchdog.features.incidentManagement.domain.repository.IncidentManagementRepository;

@Service
public class FetchAddressByIdUseCase implements UseCase<Long, Address> {
    private final IncidentManagementRepository incidentManagementRepository;

    FetchAddressByIdUseCase(IncidentManagementRepository incidentManagementRepository) {
        this.incidentManagementRepository = incidentManagementRepository;
    }

    @Override
    public Address execute(Long input) {
        return incidentManagementRepository.fetchAddressById(input).orElseThrow(() -> new ResourceNotFoundException("Address", "addressId", input));
    }
}
