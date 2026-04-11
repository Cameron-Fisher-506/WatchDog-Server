package za.co.watchdog.common.presentation.manager;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import za.co.watchdog.common.domain.exception.WatchdogException;
import za.co.watchdog.common.presentation.common.ApiErrorResponse;

import java.time.Instant;
import java.util.UUID;

@RestControllerAdvice
public class GlobalExceptionManager {
    @ExceptionHandler(WatchdogException.class)
    public ResponseEntity<ApiErrorResponse> handleWatchdogException(WatchdogException ex) {
        ApiErrorResponse error = new ApiErrorResponse(
                ex.getErrorCode(),
                ex.getMessage(),
                UUID.randomUUID().toString(),
                Instant.now()
        );

        return new ResponseEntity<>(error, ex.getStatus());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiErrorResponse> handleGeneral(Exception ex) {
        String traceId = UUID.randomUUID().toString();
        return ResponseEntity.internalServerError().body(new ApiErrorResponse(
                "INTERNAL_SERVER_ERROR",
                "An unexpected error occurred. Reference ID: " + traceId,
                traceId,
                Instant.now()
        ));
    }
}
