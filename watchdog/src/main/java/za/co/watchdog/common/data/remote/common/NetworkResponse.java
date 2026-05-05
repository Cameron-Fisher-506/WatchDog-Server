package za.co.watchdog.common.data.remote.common;

public sealed interface NetworkResponse<T> permits
        NetworkResponse.Success,
        NetworkResponse.HttpError,
        NetworkResponse.NetworkError {

    record Success<T>(T data) implements NetworkResponse<T> {}
    record HttpError<T>(String message, int statusCode) implements NetworkResponse<T> {}
    record NetworkError<T>(String message) implements NetworkResponse<T> {}
}
