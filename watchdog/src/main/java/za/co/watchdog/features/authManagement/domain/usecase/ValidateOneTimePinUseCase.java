package za.co.watchdog.features.authManagement.domain.usecase;

import org.springframework.stereotype.Service;
import za.co.watchdog.common.domain.model.User;
import za.co.watchdog.common.domain.model.oneTimePin.ValidateOneTimePin;
import za.co.watchdog.common.domain.usecase.UseCase;
import za.co.watchdog.common.domain.exception.ResourceNotFoundException;
import za.co.watchdog.features.authManagement.domain.repository.AuthManagementRepository;

import java.time.Instant;

@Service
public class ValidateOneTimePinUseCase implements UseCase<ValidateOneTimePin, Boolean> {
    private final AuthManagementRepository authManagementRepository;

    ValidateOneTimePinUseCase(AuthManagementRepository authManagementRepository) {
        this.authManagementRepository = authManagementRepository;
    }
    @Override
    public Boolean execute(ValidateOneTimePin validateOneTimePin) {
        User user = authManagementRepository.fetchUserById(validateOneTimePin.getUserId())
                .orElseThrow(() -> new ResourceNotFoundException("ValidateOneTimePin", "userId", validateOneTimePin.getUserId()));
        if (user.getVerificationCode() != null && user.getVerificationCodeExpiresAt() != null) {
            return user.getVerificationCode().equals(validateOneTimePin.getOneTimePin()) && user.getVerificationCodeExpiresAt().isBefore(Instant.now());
        } else {
            throw new ResourceNotFoundException("ValidateOneTimePin", "userId", validateOneTimePin.getUserId());
        }
    }
}
