package za.co.watchdog.features.authManagement.domain.usecase;

import org.springframework.stereotype.Service;
import za.co.watchdog.common.domain.model.User;
import za.co.watchdog.common.domain.usecase.UseCase;
import za.co.watchdog.common.domain.exception.ResourceNotFoundException;
import za.co.watchdog.features.authManagement.domain.repository.AuthManagementRepository;

import java.security.SecureRandom;
import java.time.Duration;
import java.time.Instant;

@Service
public class GenerateOneTimePinUseCase implements UseCase<User, Boolean> {
    private final SecureRandom random = new SecureRandom();
    private final AuthManagementRepository authManagementRepository;

    GenerateOneTimePinUseCase(AuthManagementRepository authManagementRepository) {
        this.authManagementRepository = authManagementRepository;
    }

    @Override
    public Boolean execute(User user) {
        user.setVerificationCode(String.format("%6d", random.nextInt(999999)));
        user.setVerificationCodeExpiresAt(Instant.now().plus(Duration.ofMinutes(10)));
        return authManagementRepository.sendOtp(user)
                .orElseThrow(() -> new ResourceNotFoundException("OTP", "user", user));
    }
}
