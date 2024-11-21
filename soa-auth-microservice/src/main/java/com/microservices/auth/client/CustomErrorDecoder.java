package com.microservices.auth.client;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.microservices.auth.exceptions.GenericErrorResponse;
import com.microservices.auth.exceptions.ValidationException;
import feign.Response;
import feign.codec.ErrorDecoder;
import org.apache.commons.io.IOUtils;
import org.springframework.http.HttpStatus;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

public class CustomErrorDecoder implements ErrorDecoder {

    private final ObjectMapper mapper = new ObjectMapper();

    @Override
    public Exception decode(String methodKey, Response response) {
        try (InputStream body = response.body().asInputStream()) {
            String responseBody = IOUtils.toString(body, StandardCharsets.UTF_8);
            Map<String, String> errors = parseResponseBody(responseBody);

            if (response.status() == 400) {
                return ValidationException.builder()
                        .validationErrors(errors)
                        .build();
            } else {
                return GenericErrorResponse.builder()
                        .httpStatus(HttpStatus.valueOf(response.status()))
                        .message(errors.getOrDefault("error", "Unknown error"))
                        .build();
            }
        } catch (IOException exception) {
            return GenericErrorResponse.builder()
                    .httpStatus(HttpStatus.valueOf(response.status()))
                    .message("Error decoding response: " + exception.getMessage())
                    .build();
        }
    }

    private Map<String, String> parseResponseBody(String responseBody) {
        try {

            return mapper.readValue(responseBody, Map.class);
        } catch (IOException e) {

            Map<String, String> fallbackError = new HashMap<>();
            fallbackError.put("error", responseBody);
            return fallbackError;
        }
    }
}
