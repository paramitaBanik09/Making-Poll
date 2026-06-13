package com.paramita.poll.exception;

import com.paramita.poll.exception.errorResponse.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> methodArgNotValidExceptionHandler(MethodArgumentNotValidException exp) {
        ErrorResponse error = new ErrorResponse();
        List<String> msgList = new ArrayList<>();
        exp.getBindingResult().getFieldErrors().forEach(e->{
            msgList.add(e.getField()+" "+e.getDefaultMessage());
        });
        error.setMessage(msgList);
        error.setErrorCode(exp.getStatusCode().toString());
        error.setTimestamp(Instant.now());
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value=UniqueConstraintException.class)
    public ResponseEntity<ErrorResponse> handleUniqueConstraintException(UniqueConstraintException exp) {
        ErrorResponse error = new ErrorResponse();
        List<String> msgList = new ArrayList<>();
        msgList.add(exp.getMessage());
        error.setMessage(msgList);
        error.setErrorCode(HttpStatus.BAD_REQUEST.toString());
        error.setTimestamp(Instant.now());
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }
}
