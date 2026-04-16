package za.co.watchdog.features.authManagement.domain.usecase;

import org.springframework.stereotype.Service;
import za.co.watchdog.common.domain.exception.ResourceNotFoundException;
import za.co.watchdog.common.domain.manager.UniqueIdentifierGenerator;
import za.co.watchdog.common.domain.model.Device;
import za.co.watchdog.common.domain.usecase.UseCase;
import za.co.watchdog.features.authManagement.domain.repository.AuthManagementRepository;

@Service
public class SaveUserDeviceUseCase implements UseCase<Device, Device> {
    private final AuthManagementRepository authManagementRepository;
    private final UniqueIdentifierGenerator uniqueIdentifierGenerator;

    SaveUserDeviceUseCase(AuthManagementRepository authManagementRepository, UniqueIdentifierGenerator uniqueIdentifierGenerator) {
        this.authManagementRepository = authManagementRepository;
        this.uniqueIdentifierGenerator = uniqueIdentifierGenerator;
    }

    @Override
    public Device execute(Device input) {
        input.setDeviceFingerprint(uniqueIdentifierGenerator.generate());
        return this.authManagementRepository.saveUserDevice(input).orElseThrow(() -> new ResourceNotFoundException("SaveUserDevice", "device", input));
    }
}
