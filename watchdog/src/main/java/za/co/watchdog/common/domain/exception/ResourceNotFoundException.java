package za.co.watchdog.common.domain.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends WatchdogException {

    public ResourceNotFoundException(String resourceName, String fieldName, Object fieldValue) {
        super(
                String.format("%s not found with %s: '%s'", resourceName, fieldName, fieldValue),
                String.format("%s_NOT_FOUND", resourceName.toUpperCase()),
                HttpStatus.NOT_FOUND
        );
    }
}
