package za.co.watchdog.features.authManagement.presentation.mapper;

import org.springframework.stereotype.Component;
import za.co.watchdog.common.domain.manager.TokenManager;
import za.co.watchdog.common.domain.model.Device;
import za.co.watchdog.common.domain.model.User;
import za.co.watchdog.common.domain.model.oneTimePin.ValidateOneTimePin;
import za.co.watchdog.features.authManagement.presentation.model.login.LoginResponseDto;
import za.co.watchdog.features.authManagement.presentation.model.otp.ValidateOneTimePinResponseDto;
import za.co.watchdog.features.authManagement.presentation.model.register.RegisterResponseDto;
import za.co.watchdog.features.authManagement.presentation.model.otp.ValidateOneTimePinRequestDto;
import za.co.watchdog.features.authManagement.presentation.model.login.LoginRequestDto;
import za.co.watchdog.features.authManagement.presentation.model.register.RegisterRequestDto;
import za.co.watchdog.common.presentation.model.UserRole;
import za.co.watchdog.common.presentation.model.UserStatus;
import za.co.watchdog.features.authManagement.presentation.model.register.dto.DeviceDto;

@Component
public class AuthPresenterMapper {
    private final TokenManager tokenManager;

    AuthPresenterMapper(TokenManager tokenManager) {
        this.tokenManager = tokenManager;
    }

    public RegisterResponseDto mapToRegisterResponseDto(User user, Device device, Boolean isOtpSent) {
        return RegisterResponseDto.builder()
                .userId(user.getUserId())
                .deviceFingerprint(device.getDeviceFingerprint())
                .token(tokenManager.generateToken(user.getEmailAddress(), user.getUserRole().name()))
                .isOtpSent(isOtpSent)
                .status(mapToUserStatus(user.getUserStatus()))
                .build();
    }

    public LoginResponseDto mapToLoginResponseDto(User user, Boolean isOtpRequired) {
        return LoginResponseDto.builder()
                .userId(user.getUserId())
                .token(tokenManager.generateToken(user.getEmailAddress(), user.getUserRole().name()))
                .isOtpRequired(isOtpRequired)
                .status(mapToUserStatus(user.getUserStatus()))
                .build();
    }

    public User mapToUser(RegisterRequestDto registerRequestDto) {
        return User.builder()
                .userId(registerRequestDto.getUserId())
                .emailAddress(registerRequestDto.getEmailAddress())
                .password(registerRequestDto.getPassword())
                .createdAt(registerRequestDto.getCreatedAt())
                .userRole(mapToUserRole(registerRequestDto.getUserRole()))
                .build();
    }

    public Device mapToDevice(DeviceDto deviceDto, Long userId) {
        return Device.builder()
                .deviceName(deviceDto.getDeviceName())
                .userId(userId)
                .build();
    }

    public User mapToUser(LoginRequestDto loginRequestDto) {
        return User.builder()
                .emailAddress(loginRequestDto.emailAddress)
                .password(loginRequestDto.password)
                .build();
    }

    public ValidateOneTimePin mapToValidateOneTimePin(ValidateOneTimePinRequestDto validateOneTimePinRequestDto) {
        return ValidateOneTimePin.builder()
                .userId(validateOneTimePinRequestDto.getUserId())
                .oneTimePin(validateOneTimePinRequestDto.getOneTimePin())
                .build();
    }

    public ValidateOneTimePinResponseDto mapToValidateOneTimePinResponseDto(Boolean isOtpValid) {
        return ValidateOneTimePinResponseDto.builder()
                .isValid(isOtpValid)
                .build();
    }

    private UserRole mapToUserRole(za.co.watchdog.common.domain.model.UserRole userRole) {
        return UserRole.valueOf(userRole.name());
    }

    private za.co.watchdog.common.domain.model.UserRole mapToUserRole(UserRole userRole) {
        return za.co.watchdog.common.domain.model.UserRole.valueOf(userRole.name());
    }

    private UserStatus mapToUserStatus(za.co.watchdog.common.domain.model.UserStatus userStatus) {
        return UserStatus.valueOf(userStatus.name());
    }

    private za.co.watchdog.common.domain.model.UserStatus mapToUserStatus(UserStatus userStatus) {
        return za.co.watchdog.common.domain.model.UserStatus.valueOf(userStatus.name());
    }
}
