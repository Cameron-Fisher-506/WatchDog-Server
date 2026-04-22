package za.co.watchdog.common.domain.repository;

import za.co.watchdog.common.domain.model.User;

import java.util.Optional;

public interface UserManagementRepository {
    public Optional<User> fetchUserByUsername(String username);
}
