package za.co.watchdog.features.authManagement.domain.repository;

import za.co.watchdog.common.domain.common.Result;
import za.co.watchdog.common.domain.model.User;

public interface AuthManagementRepository {
    public Result<User> fetchUser(User user);
    public Result<User> register(User user);
}
