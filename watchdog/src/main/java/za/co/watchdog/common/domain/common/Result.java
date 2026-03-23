package za.co.watchdog.common.domain.common;

public sealed interface Result<T> {
    record Success<T>(T data) implements Result<T> {}
    record Error<T>(String message) implements Result<T> {}

    static <T> Result<T> success(T data) {
        return new Success<>(data);
    }

    static <T> Result<T> error(String message) {
        return new Error<>(message);
    }
}
