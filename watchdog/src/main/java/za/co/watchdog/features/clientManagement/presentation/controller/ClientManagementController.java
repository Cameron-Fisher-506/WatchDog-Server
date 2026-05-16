package za.co.watchdog.features.clientManagement.presentation.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import za.co.watchdog.common.domain.model.AccountStatus;
import za.co.watchdog.common.domain.usecase.FetchCurrentUserByUsernameUseCase;
import za.co.watchdog.features.clientManagement.domain.model.Client;
import za.co.watchdog.features.clientManagement.domain.usecase.FetchVehicleByZoneIdUseCase;
import za.co.watchdog.features.clientManagement.domain.usecase.SaveAddressUseCase;
import za.co.watchdog.common.domain.model.User;
import za.co.watchdog.common.presentation.common.ApiSuccessResponse;
import za.co.watchdog.features.authManagement.domain.usecase.SaveUserUseCase;
import za.co.watchdog.features.clientManagement.domain.usecase.ClientOnboardingUseCase;
import za.co.watchdog.features.clientManagement.domain.usecase.UpdateClientByUserIdUseCase;
import za.co.watchdog.features.clientManagement.presentation.mapper.ClientPresenterMapper;
import za.co.watchdog.features.clientManagement.presentation.model.clientOnboarding.ClientOnboardingRequestDto;
import za.co.watchdog.features.clientManagement.presentation.model.clientOnboarding.ClientOnboardingResponseDto;
import za.co.watchdog.features.clientManagement.presentation.model.updateProfileInformation.UpdateProfileInformationRequestDto;
import za.co.watchdog.features.clientManagement.presentation.model.updateProfileInformation.UpdateProfileInformationResponseDto;

@RestController
@RequestMapping("api/v1/clientmanagement")
public class ClientManagementController {
    private final ClientOnboardingUseCase clientOnboardingUseCase;
    private final FetchCurrentUserByUsernameUseCase fetchCurrentUserByUsernameUseCase;
    private final SaveUserUseCase saveUserUseCase;
    private final SaveAddressUseCase saveAddressUseCase;
    private final UpdateClientByUserIdUseCase updateClientByUserIdUseCase;
    private final ClientPresenterMapper clientPresenterMapper;

    public ClientManagementController(
            ClientOnboardingUseCase clientOnboardingUseCase,
            FetchCurrentUserByUsernameUseCase fetchCurrentUserByUsernameUseCase,
            SaveUserUseCase saveUserUseCase,
            SaveAddressUseCase saveAddressUseCase,
            UpdateClientByUserIdUseCase updateClientByUserIdUseCase,
            ClientPresenterMapper clientPresenterMapper
    ) {
        this.clientOnboardingUseCase = clientOnboardingUseCase;
        this.fetchCurrentUserByUsernameUseCase = fetchCurrentUserByUsernameUseCase;
        this.saveUserUseCase = saveUserUseCase;
        this.saveAddressUseCase = saveAddressUseCase;
        this.updateClientByUserIdUseCase = updateClientByUserIdUseCase;
        this.clientPresenterMapper = clientPresenterMapper;
    }

    @PostMapping("/onboarding")
    public ResponseEntity<ApiSuccessResponse<ClientOnboardingResponseDto>> register(@RequestBody ClientOnboardingRequestDto clientOnboardingRequestDto) {
        User user = this.fetchCurrentUserByUsernameUseCase.execute(null);
        Client client = this.clientOnboardingUseCase.execute(clientPresenterMapper.mapToClient(clientOnboardingRequestDto, user.getUserId()));
        this.saveAddressUseCase.execute(clientPresenterMapper.mapToAddress(clientOnboardingRequestDto.getAddressDto(), client.getClientId()));

        user.setAccountStatus(AccountStatus.ACTIVE);
        this.saveUserUseCase.execute(user);

        return new ResponseEntity<>(
                ApiSuccessResponse.ok(
                        ClientOnboardingResponseDto.builder()
                                .title("Onboarding Successful")
                                .message("Welcome to Watchdog. Your account has been successfully created and your onboarding is complete. You now have full access to our security management features to help keep your environment safe and monitored.")
                                .build()),
                HttpStatus.OK
        );
    }

    @PostMapping("/updatePersonalInformation")
    public ResponseEntity<ApiSuccessResponse<UpdateProfileInformationResponseDto>> updateProfileInformation(@RequestBody UpdateProfileInformationRequestDto updateProfileInformationRequestDto) {
        User user = this.fetchCurrentUserByUsernameUseCase.execute(null);
        Client client = updateClientByUserIdUseCase.execute(clientPresenterMapper.mapToClient(updateProfileInformationRequestDto, user.getUserId()));
        return new ResponseEntity<>(
                ApiSuccessResponse.ok(
                        UpdateProfileInformationResponseDto.builder()
                                .title("Successful")
                                .message("Updated personal information successfully.")
                                .build()),
                HttpStatus.OK
        );
    }
}