package za.co.watchdog.features.authManagement.presentation.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import za.co.watchdog.common.domain.common.Result;
import za.co.watchdog.features.authManagement.domain.model.AuthenticatedUser;
import za.co.watchdog.features.authManagement.domain.usecase.RegisterUserUseCase;
import za.co.watchdog.features.authManagement.presentation.mapper.AuthPresenterMapper;
import za.co.watchdog.features.authManagement.presentation.model.register.RegisterRequestDto;
import za.co.watchdog.features.authManagement.presentation.model.AuthResponseDto;
import za.co.watchdog.features.authManagement.presentation.model.login.LoginRequestDto;

@RestController
@RequestMapping("api/v1/authmanagement")
public class AuthManagementController {
    private final RegisterUserUseCase registerUserUseCase;
    private final AuthPresenterMapper authPresenterMapper;

    public AuthManagementController(RegisterUserUseCase registerUserUseCase, AuthPresenterMapper authPresenterMapper) {
        this.registerUserUseCase = registerUserUseCase;
        this.authPresenterMapper = authPresenterMapper;
    }

    @PostMapping("/register")
    public ResponseEntity<AuthResponseDto> register(@RequestBody RegisterRequestDto registerRequestDto) {
        Result<AuthenticatedUser> result = this.registerUserUseCase.execute(authPresenterMapper.mapToUser(registerRequestDto));
        switch (result) {
            case Result.Success<AuthenticatedUser> success -> {
                return new ResponseEntity<>(
                        authPresenterMapper.mapToAuthResponseDto(
                                success.data()
                        ),
                        HttpStatus.OK
                );
            }

            case Result.Error<AuthenticatedUser> error -> {
                return new ResponseEntity<>(
                        HttpStatus.CONFLICT
                );
            }
        }
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponseDto> login(@RequestBody LoginRequestDto loginRequestDto) {
        return null;
    }
}
