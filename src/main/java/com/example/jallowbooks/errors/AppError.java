package com.example.jallowbooks.errors;

import java.time.LocalDateTime;

public record AppError(
        String path,
        String message,
        int statusCode,
        LocalDateTime localDateTime
) {
}
