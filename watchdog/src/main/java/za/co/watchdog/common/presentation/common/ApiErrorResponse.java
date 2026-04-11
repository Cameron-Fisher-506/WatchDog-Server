package za.co.watchdog.common.presentation.common;

import java.time.Instant;

public record ApiErrorResponse(
        String code,
        String message,
        String traceId,
        Instant timestamp
) {}
