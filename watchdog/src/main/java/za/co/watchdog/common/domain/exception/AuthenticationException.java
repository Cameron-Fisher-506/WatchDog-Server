package za.co.watchdog.common.domain.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNAUTHORIZED)
public class AuthenticationException extends WatchdogException {
    public AuthenticationException() {
        super(
                "Invalid username or password",
                "UNAUTHORIZED",
                HttpStatus.UNAUTHORIZED
        );
    }
}