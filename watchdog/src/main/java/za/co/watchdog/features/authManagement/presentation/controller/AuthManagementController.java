package za.co.watchdog.features.authManagement.presentation.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import za.co.watchdog.common.domain.common.Result;
import za.co.watchdog.features.authManagement.domain.model.Client;
import za.co.watchdog.features.authManagement.domain.usecase.RegisterClientUseCase;
import za.co.watchdog.features.authManagement.presentation.mapper.ClientMapper;
import za.co.watchdog.features.authManagement.presentation.model.client.ClientRequestDto;
import za.co.watchdog.features.authManagement.presentation.model.client.ClientResponseDto;

@RestController
@RequestMapping("api/v1/authmanagement")
public class AuthManagementController {
    private final RegisterClientUseCase registerClientUseCase;
    private final ClientMapper clientMapper;

    public AuthManagementController(RegisterClientUseCase registerClientUseCase, ClientMapper clientMapper) {
        this.registerClientUseCase = registerClientUseCase;
        this.clientMapper = clientMapper;
    }

    @PostMapping("/register")
    public ResponseEntity<ClientResponseDto> registerClient(@RequestBody ClientRequestDto clientRequestDto) {
        Result<Client> result = this.registerClientUseCase.execute(clientMapper.mapToClient(clientRequestDto));
        switch (result) {
            case Result.Success<Client> success -> {
                return new ResponseEntity<>(

                        clientMapper.mapToClientResponseDto(
                                success.data(),
                                ""
                        ),
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
