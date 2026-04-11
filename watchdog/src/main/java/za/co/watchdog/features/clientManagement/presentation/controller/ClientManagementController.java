package za.co.watchdog.features.clientManagement.presentation.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import za.co.watchdog.common.domain.model.Client;
import za.co.watchdog.common.presentation.common.ApiSuccessResponse;
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
    public ResponseEntity<ApiSuccessResponse<ClientOnboardingResponseDto>> register(@RequestBody ClientOnboardingRequestDto clientOnboardingRequestDto) {
        Client client = this.clientOnboardingUseCase.execute(clientPresenterMapper.mapToClient(clientOnboardingRequestDto));
        return new ResponseEntity<>(
                ApiSuccessResponse.ok(
                        ClientOnboardingResponseDto.builder()
                                .title("Onboarding Successful")
                                .message("Welcome to Watchdog. Your account has been successfully created and your onboarding is complete. You now have full access to our security management features to help keep your environment safe and monitored.")
                                .build()),
                HttpStatus.OK
        );
    }
}