package za.co.watchdog.features.authManagement.domain.usecase;

import org.springframework.stereotype.Component;
import za.co.watchdog.common.data.manager.security.config.SecurityConfig;
import za.co.watchdog.common.domain.common.Result;
import za.co.watchdog.common.domain.model.User;
import za.co.watchdog.common.domain.usecase.UseCase;
import za.co.watchdog.features.authManagement.domain.repository.AuthManagementRepository;

@Component
public class LoginUserUseCase implements UseCase<User, Result<User>> {
    private final AuthManagementRepository authManagementRepository;
    private final SecurityConfig securityConfig;

    LoginUserUseCase(AuthManagementRepository authManagementRepository, SecurityConfig securityConfig) {
        this.authManagementRepository = authManagementRepository;
        this.securityConfig = securityConfig;
    }

    @Override
    public Result<User> execute(User input) {
        Result<User> result = authManagementRepository.fetchUser(input);
        switch (result) {
            case Result.Success<User> success -> {
                if (securityConfig.passwordEncoder().matches(input.getPassword(), success.data().getPassword())) {
                    return Result.success(success.data());
                } else {
                    return Result.error("Incorrect email address or password.");
                }
            }

            case Result.Error<User> error -> {
                return Result.error(error.message());
            }
        }
    }
}
