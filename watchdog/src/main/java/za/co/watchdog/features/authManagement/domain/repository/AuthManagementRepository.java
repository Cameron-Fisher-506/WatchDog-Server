package za.co.watchdog.features.authManagement.domain.repository;

import za.co.watchdog.common.domain.model.Device;
import za.co.watchdog.common.domain.model.User;

import java.util.Optional;

public interface AuthManagementRepository {
    public Optional<User> fetchUserByEmailAddress(String emailAddress);
    public Optional<Device> fetchDeviceByDeviceFingerprint(String deviceFingerprint);
    public Optional<Device> fetchDeviceByUserId(Long userId);
    public Optional<User> fetchUserById(Long userId);
    public Optional<User> saveUser(User user);
    public Optional<Boolean> sendOtp(User user);
    public Optional<Device> saveDevice(Device device);
}
