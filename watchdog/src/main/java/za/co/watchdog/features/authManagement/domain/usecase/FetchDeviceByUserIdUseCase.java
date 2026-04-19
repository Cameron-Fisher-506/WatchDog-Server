package za.co.watchdog.features.authManagement.domain.usecase;

import org.springframework.stereotype.Service;
import za.co.watchdog.common.domain.exception.ResourceNotFoundException;
import za.co.watchdog.common.domain.model.Device;
import za.co.watchdog.common.domain.usecase.UseCase;
import za.co.watchdog.features.authManagement.domain.repository.AuthManagementRepository;

@Service
public class FetchDeviceByUserIdUseCase implements UseCase<Long, Device> {
    private final AuthManagementRepository authManagementRepository;

    FetchDeviceByUserIdUseCase(AuthManagementRepository authManagementRepository) {
        this.authManagementRepository = authManagementRepository;
    }

    @Override
    public Device execute(Long input) {
        return this.authManagementRepository.fetchDeviceByUserId(input).orElseThrow(() -> new ResourceNotFoundException("FetchDeviceByUserId", "userId", input));
    }
}
