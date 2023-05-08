package com.droneservice.payload.response;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ApiErrorResponse {
    String path;
    String message;
    int statusCode;
    LocalDateTime createdAt;
}
