package za.co.watchdog.features.clientManagement.presentation.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import za.co.watchdog.common.domain.common.Result;
import za.co.watchdog.features.clientManagement.domain.model.Client;
import za.co.watchdog.features.clientManagement.domain.usecase.RegisterClientUseCase;
import za.co.watchdog.features.clientManagement.presentation.mapper.ClientMapper;
import za.co.watchdog.features.clientManagement.presentation.model.ClientDto;

@RestController
@RequestMapping("api/v1/clientmanagement")
public class ClientManagementController {
    private final RegisterClientUseCase registerClientUseCase;

    public ClientManagementController(RegisterClientUseCase registerClientUseCase) {
        this.registerClientUseCase = registerClientUseCase;
    }

    @PostMapping("/register")
    public ResponseEntity<ClientDto> registerClient(@RequestBody ClientDto clientDto) {
        Result<Client> result = this.registerClientUseCase.execute(ClientMapper.mapToClient(clientDto));
        switch (result) {
            case Result.Success<Client> success -> {
                return new ResponseEntity<>(
                        ClientMapper.mapToClientDto(success.data()),
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
