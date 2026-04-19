package za.co.watchdog.features.authManagement.domain.usecase;

import org.springframework.stereotype.Service;
import za.co.watchdog.common.domain.model.AccountStatus;
import za.co.watchdog.common.domain.model.User;
import za.co.watchdog.common.domain.model.UserStatus;
import za.co.watchdog.common.domain.model.oneTimePin.ValidateOneTimePin;
import za.co.watchdog.common.domain.usecase.UseCase;
import za.co.watchdog.common.domain.exception.ResourceNotFoundException;
import za.co.watchdog.features.authManagement.domain.repository.AuthManagementRepository;

import java.time.Instant;
import java.util.Optional;

@Service
public class ValidateOneTimePinUseCase implements UseCase<ValidateOneTimePin, Boolean> {
    private final AuthManagementRepository authManagementRepository;

    ValidateOneTimePinUseCase(AuthManagementRepository authManagementRepository) {
        this.authManagementRepository = authManagementRepository;
    }
    @Override
    public Boolean execute(ValidateOneTimePin validateOneTimePin) {
        Optional<User> optionalUser = authManagementRepository.fetchUserById(validateOneTimePin.getUserId());
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            if (user.getVerificationCode() != null && user.getVerificationCodeExpiresAt() != null && user.getVerificationCode().equals(validateOneTimePin.getOneTimePin()) && Instant.now().isBefore(user.getVerificationCodeExpiresAt())) {
                user.setUserStatus(UserStatus.ACTIVE);
                user.setAccountStatus(AccountStatus.VERIFIED_PENDING_ONBOARDING);
                authManagementRepository.saveUser(user);
                return true;
            } else {
                return false;
            }
        } else {
            throw new ResourceNotFoundException("ValidateOneTimePin", "userId", validateOneTimePin.getUserId());
        }
    }
}
