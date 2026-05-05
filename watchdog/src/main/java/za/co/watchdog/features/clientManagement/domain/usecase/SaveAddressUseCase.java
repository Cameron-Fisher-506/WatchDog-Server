package za.co.watchdog.features.clientManagement.domain.usecase;

import org.springframework.stereotype.Service;
import za.co.watchdog.common.domain.exception.ResourceNotFoundException;
import za.co.watchdog.common.domain.usecase.UseCase;
import za.co.watchdog.features.clientManagement.domain.model.Address;
import za.co.watchdog.features.clientManagement.domain.repository.ClientManagementRepository;

@Service
public class SaveAddressUseCase implements UseCase<Address, Address> {
    private final ClientManagementRepository clientManagementRepository;

    SaveAddressUseCase(ClientManagementRepository clientManagementRepository) {
        this.clientManagementRepository = clientManagementRepository;
    }

    @Override
    public Address execute(Address input) {
        return clientManagementRepository.saveAddress(input).orElseThrow(() -> new ResourceNotFoundException("Address", "address", input));
    }
}
