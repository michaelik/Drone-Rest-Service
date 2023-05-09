package com.droneservice.exception.advice;

import com.droneservice.exception.InsufficientWeightException;
import com.droneservice.exception.LowBatteryException;
import com.droneservice.exception.ResourceNotFoundException;
import com.droneservice.payload.response.ApiErrorResponse;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestControllerAdvice
public class DefaultExceptionHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(
                                     HttpRequestMethodNotSupportedException ex,
                                     HttpHeaders headers,
                                     HttpStatus status,
                                     WebRequest request) {
        ApiErrorResponse apiError = new ApiErrorResponse(
                request.getContextPath(),
                ex.getMessage(),
                status.value(),
                LocalDateTime.now()
        );

        return new ResponseEntity<>(apiError, status);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
                                     MethodArgumentNotValidException ex,
                                     HttpHeaders headers,
                                     HttpStatus status,
                                     WebRequest request) {
        Map<String, Object> body = new LinkedHashMap<>();
        // get all errors
        List<String> errors = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .collect(Collectors.toList());
        body.put("path", request.getContextPath());
        body.put("errors", errors);
        body.put("status", status.value());
        body.put("timestamp", LocalDateTime.now());
        return new ResponseEntity<>(body, status);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiErrorResponse> handleException(ResourceNotFoundException e,
                                                    HttpServletRequest request) {
        ApiErrorResponse apiError = new ApiErrorResponse(
                request.getRequestURI(),
                e.getMessage(),
                HttpStatus.NOT_FOUND.value(),
                LocalDateTime.now()
        );

        return new ResponseEntity<>(apiError, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(InsufficientWeightException.class)
    public ResponseEntity<ApiErrorResponse> handleException(InsufficientWeightException e,
                                                            HttpServletRequest request) {
        ApiErrorResponse apiError = new ApiErrorResponse(
                request.getRequestURI(),
                e.getMessage(),
                HttpStatus.BAD_REQUEST.value(),
                LocalDateTime.now()
        );

        return new ResponseEntity<>(apiError, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(LowBatteryException.class)
    public ResponseEntity<ApiErrorResponse> handleException(LowBatteryException e,
                                                            HttpServletRequest request) {
        ApiErrorResponse apiError = new ApiErrorResponse(
                request.getRequestURI(),
                e.getMessage(),
                HttpStatus.BAD_REQUEST.value(),
                LocalDateTime.now()
        );

        return new ResponseEntity<>(apiError, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(InsufficientAuthenticationException.class)
    public ResponseEntity<ApiErrorResponse> handleException(InsufficientAuthenticationException e,
                                                    HttpServletRequest request) {
        ApiErrorResponse apiError = new ApiErrorResponse(
                request.getRequestURI(),
                e.getMessage(),
                HttpStatus.FORBIDDEN.value(),
                LocalDateTime.now()
        );

        return new ResponseEntity<>(apiError, HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<ApiErrorResponse> handleException(BadCredentialsException e,
                                                    HttpServletRequest request) {
        ApiErrorResponse apiError = new ApiErrorResponse(
                request.getRequestURI(),
                e.getMessage(),
                HttpStatus.UNAUTHORIZED.value(),
                LocalDateTime.now()
        );

        return new ResponseEntity<>(apiError, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiErrorResponse> handleException(Exception e,
                                                    HttpServletRequest request) {
        ApiErrorResponse apiError = new ApiErrorResponse(
                request.getRequestURI(),
                e.getMessage(),
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                LocalDateTime.now()
        );

        return new ResponseEntity<>(apiError, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
