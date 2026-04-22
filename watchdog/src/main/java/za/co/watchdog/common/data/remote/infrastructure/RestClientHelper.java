package za.co.watchdog.common.data.remote.infrastructure;

import org.springframework.web.client.RestClientResponseException;
import za.co.watchdog.common.data.remote.common.NetworkResponse;

import java.util.function.Supplier;

public class  RestClientHelper<T> {
    public static <T> NetworkResponse<T> getNetworkResponse(Supplier<T> serviceCall) {
        try {
            T data = serviceCall.get();
            return new NetworkResponse.Success<>(data);
        } catch (RestClientResponseException e) {
            int statusCode = e.getStatusCode().value();
            if (statusCode == 404) {
                return new NetworkResponse.HttpError<>("Not Found", statusCode);
            } else if (statusCode == 500) {
                return new NetworkResponse.HttpError<>("Internal Server Error", statusCode);
            } else {
                String message = e.getResponseBodyAs(String.class);
                return new NetworkResponse.HttpError<>(
                        "HTTP Error " + statusCode + ": " + message,
                        statusCode
                );
            }
        } catch (Exception e) {
            return new NetworkResponse.NetworkError<>(e.getMessage() != null ? e.getMessage() : "Unknown Error");
        }
    }
}
