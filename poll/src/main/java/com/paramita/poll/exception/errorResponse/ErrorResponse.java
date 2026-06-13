package com.paramita.poll.exception.errorResponse;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.Instant;

@Data
public class ErrorResponse {
    private String message;
    private String errorCode;
    private Instant timestamp;
}
