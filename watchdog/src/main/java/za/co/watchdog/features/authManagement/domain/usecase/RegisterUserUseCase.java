package za.co.watchdog.features.authManagement.domain.usecase;

import org.springframework.stereotype.Component;
import za.co.watchdog.common.data.manager.security.config.SecurityConfig;
import za.co.watchdog.common.domain.common.Result;
import za.co.watchdog.common.domain.manager.TokenManager;
import za.co.watchdog.common.domain.model.User;
import za.co.watchdog.common.domain.usecase.UseCase;
import za.co.watchdog.features.authManagement.domain.repository.AuthManagementRepository;

@Component
public class RegisterUserUseCase implements UseCase<User, Result<User>> {
    private final AuthManagementRepository authManagementRepository;
    private final SecurityConfig securityConfig;

    public RegisterUserUseCase(AuthManagementRepository authManagementRepository, SecurityConfig securityConfig) {
        this.authManagementRepository = authManagementRepository;
        this.securityConfig = securityConfig;
    }

    @Override
    public Result<User> execute(User user) {
        Result<User> result = this.authManagementRepository.fetchUser(user);
        switch (result) {
            case Result.Success<User> success -> {
                return Result.error("Account already exists");
            }

            case Result.Error<User> error -> {
                user.setPassword(securityConfig.passwordEncoder().encode(user.getPassword()));
                Result<User> registerUserResult = this.authManagementRepository.register(user);
                switch (registerUserResult) {
                    case Result.Success<User> success -> {
                        return Result.success(success.data());
                    }

                    case Result.Error<User> resgisterClientError -> {
                        return Result.error(resgisterClientError.message());
                    }
                }
            }
        }
    }
}
