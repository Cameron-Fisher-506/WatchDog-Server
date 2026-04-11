package za.co.watchdog.features.authManagement.presentation.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import za.co.watchdog.common.domain.model.User;
import za.co.watchdog.common.presentation.common.ApiSuccessResponse;
import za.co.watchdog.features.authManagement.domain.usecase.FetchUserByIdUseCase;
import za.co.watchdog.features.authManagement.domain.usecase.GenerateOneTimePinUseCase;
import za.co.watchdog.features.authManagement.domain.usecase.LoginUserUseCase;
import za.co.watchdog.features.authManagement.domain.usecase.RegisterUserUseCase;
import za.co.watchdog.features.authManagement.presentation.mapper.AuthPresenterMapper;
import za.co.watchdog.features.authManagement.presentation.model.login.LoginResponseDto;
import za.co.watchdog.features.authManagement.presentation.model.register.RegisterResponseDto;
import za.co.watchdog.features.authManagement.presentation.model.login.LoginRequestDto;
import za.co.watchdog.features.authManagement.presentation.model.register.RegisterRequestDto;
import za.co.watchdog.features.authManagement.presentation.model.resendOtp.ResendOtpRequestDto;
import za.co.watchdog.features.authManagement.presentation.model.resendOtp.ResendOtpResponseDto;

@RestController
@RequestMapping("api/v1/authmanagement")
public class AuthManagementController {
    private final RegisterUserUseCase registerUserUseCase;
    private final GenerateOneTimePinUseCase generateOneTimePinUseCase;
    private final LoginUserUseCase loginUserUseCase;
    private final FetchUserByIdUseCase fetchUserByIdUseCase;
    private final AuthPresenterMapper authPresenterMapper;

    public AuthManagementController(RegisterUserUseCase registerUserUseCase, GenerateOneTimePinUseCase generateOneTimePinUseCase, LoginUserUseCase loginUserUseCase, FetchUserByIdUseCase fetchUserByIdUseCase, AuthPresenterMapper authPresenterMapper) {
        this.registerUserUseCase = registerUserUseCase;
        this.generateOneTimePinUseCase = generateOneTimePinUseCase;
        this.loginUserUseCase = loginUserUseCase;
        this.fetchUserByIdUseCase = fetchUserByIdUseCase;
        this.authPresenterMapper = authPresenterMapper;
    }

    @PostMapping("/resend/otp")
    public ResponseEntity<ApiSuccessResponse<ResendOtpResponseDto>> sendOtp(@RequestBody ResendOtpRequestDto resendOtpRequestDto) {
        User user = fetchUserByIdUseCase.execute(resendOtpRequestDto.getUserId());
        Boolean isOtpSent = generateOneTimePinUseCase.execute(user);
        return new ResponseEntity<>(ApiSuccessResponse.ok(ResendOtpResponseDto.builder().isOtpSent(isOtpSent).build()), HttpStatus.OK);
    }

    @PostMapping("/register")
    public ResponseEntity<ApiSuccessResponse<RegisterResponseDto>> register(@RequestBody RegisterRequestDto registerRequestDto) {
        User user = this.registerUserUseCase.execute(authPresenterMapper.mapToUser(registerRequestDto));
        Boolean isOtpSent = generateOneTimePinUseCase.execute(user);
        return new ResponseEntity<>(ApiSuccessResponse.ok(authPresenterMapper.mapToRegisterResponseDto(user, isOtpSent)), HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity<ApiSuccessResponse<LoginResponseDto>> login(@RequestBody LoginRequestDto loginRequestDto) {
        User user = this.loginUserUseCase.execute(authPresenterMapper.mapToUser(loginRequestDto));
        return new ResponseEntity<>(ApiSuccessResponse.ok(authPresenterMapper.mapToLoginResponseDto(user, true)), HttpStatus.OK); //TODO: Add Use case to check deviceFingerprint
    }
}
