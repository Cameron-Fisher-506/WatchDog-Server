package za.co.watchdog.features.clientManagement.presentation.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import za.co.watchdog.common.domain.common.Result;
import za.co.watchdog.common.domain.model.Client;
import za.co.watchdog.common.domain.model.User;
import za.co.watchdog.features.authManagement.domain.usecase.LoginUserUseCase;
import za.co.watchdog.features.authManagement.domain.usecase.RegisterUserUseCase;
import za.co.watchdog.features.authManagement.presentation.mapper.AuthPresenterMapper;
import za.co.watchdog.features.authManagement.presentation.model.AuthResponseDto;
import za.co.watchdog.features.authManagement.presentation.model.login.LoginRequestDto;
import za.co.watchdog.features.authManagement.presentation.model.register.RegisterRequestDto;
import za.co.watchdog.features.clientManagement.domain.usecase.ClientOnboardingUseCase;
import za.co.watchdog.features.clientManagement.presentation.mapper.ClientPresenterMapper;
import za.co.watchdog.features.clientManagement.presentation.model.clientOnboarding.ClientOnboardingRequestDto;
import za.co.watchdog.features.clientManagement.presentation.model.clientOnboarding.ClientOnboardingResponseDto;

@RestController
@RequestMapping("api/v1/clientmanagement")
public class ClientManagementController {
    private final ClientOnboardingUseCase clientOnboardingUseCase;
    private final ClientPresenterMapper clientPresenterMapper;

    public ClientManagementController(ClientOnboardingUseCase clientOnboardingUseCase, ClientPresenterMapper clientPresenterMapper) {
        this.clientOnboardingUseCase = clientOnboardingUseCase;
        this.clientPresenterMapper = clientPresenterMapper;
    }

    @PostMapping("/onboarding")
    public ResponseEntity<ClientOnboardingResponseDto> register(@RequestBody ClientOnboardingRequestDto clientOnboardingRequestDto) {
        Result<Client> result = this.clientOnboardingUseCase.execute(clientPresenterMapper.mapToClient(clientOnboardingRequestDto));
        switch (result) {
            case Result.Success<Client> success -> {
                return new ResponseEntity<>(
                        ClientOnboardingResponseDto.builder()
                                .title("Registration Successful")
                                .message("Welcome to Watchdog. Your account has been successfully created and your onboarding is complete. You now have full access to our security management features to help keep your environment safe and monitored.")
                                .build(),
                        HttpStatus.OK
                );
            }

            case Result.Error<Client> error -> {
                return new ResponseEntity<>(
                        HttpStatus.CONFLICT
                );
            }
        }
    }
}
