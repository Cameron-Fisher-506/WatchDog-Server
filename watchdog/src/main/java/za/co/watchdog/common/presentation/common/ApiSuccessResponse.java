package za.co.watchdog.common.presentation.common;

import java.time.Instant;

public record ApiSuccessResponse<T>(
        T data,
        String message,
        Instant timestamp
) {
    public static <T> ApiSuccessResponse<T> ok(T data) {
        return new ApiSuccessResponse<>(data, "Success", Instant.now());
    }

    public static <T> ApiSuccessResponse<T> ok(T data, String message) {
        return new ApiSuccessResponse<>(data, message, Instant.now());
    }
}