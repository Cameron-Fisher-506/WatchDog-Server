package za.co.watchdog.features.authManagement.domain.usecase;

import org.springframework.stereotype.Service;
import za.co.watchdog.common.domain.exception.ResourceNotFoundException;
import za.co.watchdog.common.domain.model.Device;
import za.co.watchdog.common.domain.usecase.UseCase;
import za.co.watchdog.features.authManagement.domain.repository.AuthManagementRepository;

@Service
public class ValidateDeviceFingerprintUseCase implements UseCase<String, Device> {
    private final AuthManagementRepository authManagementRepository;

    ValidateDeviceFingerprintUseCase(AuthManagementRepository authManagementRepository) {
        this.authManagementRepository = authManagementRepository;
    }

    @Override
    public Device execute(String input) {
        return this.authManagementRepository.fetchDeviceByDeviceFingerprint(input).orElseThrow(() -> new ResourceNotFoundException("Device", "deviceFingerprint", input));
    }
}
