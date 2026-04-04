package za.co.watchdog.features.authManagement.presentation.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import za.co.watchdog.common.domain.common.Result;
import za.co.watchdog.features.authManagement.domain.model.AuthenticatedUser;
import za.co.watchdog.features.authManagement.domain.usecase.RegisterClientUseCase;
import za.co.watchdog.features.authManagement.presentation.mapper.AuthPresenterMapper;
import za.co.watchdog.features.authManagement.presentation.mapper.ClientPresenterMapper;
import za.co.watchdog.features.authManagement.presentation.model.auth.AuthResponseDto;
import za.co.watchdog.features.authManagement.presentation.model.client.ClientRequestDto;

@RestController
@RequestMapping("api/v1/authmanagement")
public class AuthManagementController {
    private final RegisterClientUseCase registerClientUseCase;
    private final ClientPresenterMapper clientPresenterMapper;
    private final AuthPresenterMapper authPresenterMapper;

    public AuthManagementController(RegisterClientUseCase registerClientUseCase, ClientPresenterMapper clientPresenterMapper, AuthPresenterMapper authPresenterMapper) {
        this.registerClientUseCase = registerClientUseCase;
        this.clientPresenterMapper = clientPresenterMapper;
        this.authPresenterMapper = authPresenterMapper;
    }

    @PostMapping("/register")
    public ResponseEntity<AuthResponseDto> registerClient(@RequestBody ClientRequestDto clientRequestDto) {
        Result<AuthenticatedUser> result = this.registerClientUseCase.execute(clientPresenterMapper.mapToClient(clientRequestDto));
        switch (result) {
            case Result.Success<AuthenticatedUser> success -> {
                return new ResponseEntity<>(

                        authPresenterMapper.mapToAuthtResponseDto(
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
}
