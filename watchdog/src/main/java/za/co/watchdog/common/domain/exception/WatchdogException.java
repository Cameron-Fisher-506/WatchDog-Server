package za.co.watchdog.common.domain.exception;

import org.springframework.http.HttpStatus;

public abstract class WatchdogException extends RuntimeException {
    private final String errorCode;
    private final HttpStatus status;

    protected WatchdogException(String message, String errorCode, HttpStatus status) {
        super(message);
        this.errorCode = errorCode;
        this.status = status;
    }

    public String getErrorCode() { return errorCode; }
    public HttpStatus getStatus() { return status; }
}
