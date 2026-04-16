package za.co.watchdog.common.data.local.mapper;

import jakarta.persistence.EntityManager;
import org.springframework.stereotype.Component;
import za.co.watchdog.common.data.local.database.model.DeviceEntity;
import za.co.watchdog.common.data.local.database.model.UserEntity;
import za.co.watchdog.common.domain.model.Device;

@Component
public class DeviceMapper {
    private final EntityManager entityManager;

    DeviceMapper(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public Device mapToDevice(DeviceEntity deviceEntity) {
        return Device.builder()
                .deviceId(deviceEntity.getDeviceId())
                .deviceFingerprint(deviceEntity.getDeviceFingerprint())
                .deviceName(deviceEntity.getDeviceName())
                .lastLoggedIn(deviceEntity.getLastLoggedIn())
                .isTrusted(deviceEntity.getIsTrusted())
                .userId(deviceEntity.getUserEntity().getUserId())
                .build();
    }

    public DeviceEntity mapToDeviceEntity(Device device) {
        return DeviceEntity.builder()
                .deviceId(device.getDeviceId())
                .deviceFingerprint(device.getDeviceFingerprint())
                .deviceName(device.getDeviceName())
                .lastLoggedIn(device.getLastLoggedIn())
                .isTrusted(device.getIsTrusted())
                .userEntity(this.entityManager.getReference(UserEntity.class, device.getUserId()))
                .build();
    }
}
