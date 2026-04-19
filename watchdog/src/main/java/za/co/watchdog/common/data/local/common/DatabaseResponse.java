package za.co.watchdog.common.data.local.common;

public sealed interface DatabaseResponse<T> {
    record Success<T>(T data) implements DatabaseResponse<T> {}
    record Error<T>(String message) implements DatabaseResponse<T> {}

    static <T> DatabaseResponse<T> success(T data) {
        return new Success<>(data);
    }

    static <T> DatabaseResponse<T> error(String message) {
        return new Error<>(message);
    }
}
