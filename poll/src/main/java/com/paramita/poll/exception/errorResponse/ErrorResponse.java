package com.paramita.poll.exception.errorResponse;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.Instant;
import java.util.List;

@Data
public class ErrorResponse {
    private List<String> message;
    private String errorCode;
    private Instant timestamp;
}
