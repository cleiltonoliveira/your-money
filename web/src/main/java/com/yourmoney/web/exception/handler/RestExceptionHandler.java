package com.yourmoney.web.exception.handler;

import com.yourmoney.usecases.exception.BadRequestException;
import com.yourmoney.usecases.exception.ResourceConflictException;
import com.yourmoney.usecases.exception.ResourceNotFoundException;
import com.yourmoney.web.exception.ErrorDetailsResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.ConstraintViolationException;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {


    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<?> handleConstraintViolationException(ConstraintViolationException exception, WebRequest request) {
        var errorDetails = buildErrorDetails(
               "Constraint violation",
                HttpStatus.BAD_REQUEST.value(),
                exception.getMessage(),
                request.getDescription(false));
        return new ResponseEntity<>(errorDetails, HttpStatus.valueOf(errorDetails.getHttpStatusCode()));
    }
    @ExceptionHandler(ResourceConflictException.class)
    public ResponseEntity<?> handleResourceConflictException(ResourceConflictException exception, WebRequest request) {
        var errorDetails = buildErrorDetails(
                "Resource conflict",
                HttpStatus.CONFLICT.value(),
                exception.getMessage(),
                request.getDescription(false));
        return new ResponseEntity<>(errorDetails, HttpStatus.valueOf(errorDetails.getHttpStatusCode()));
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<?> handleBadRequestException(BadRequestException exception, WebRequest request) {
        var errorDetails = buildErrorDetails(
                "Bad request",
                HttpStatus.BAD_REQUEST.value(),
                exception.getMessage(),
                request.getDescription(false));
        return new ResponseEntity<>(errorDetails, HttpStatus.valueOf(errorDetails.getHttpStatusCode()));
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<?> handleBadRequestException(ResourceNotFoundException exception, WebRequest request) {
        var errorDetails = buildErrorDetails(
                "Resource not found",
                HttpStatus.NOT_FOUND.value(),
                exception.getMessage(),
                request.getDescription(false));
        return new ResponseEntity<>(errorDetails, HttpStatus.valueOf(errorDetails.getHttpStatusCode()));
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException exception, HttpHeaders headers,
                                                                  HttpStatus status, WebRequest request) {
        var fieldErrors = exception.getBindingResult().getFieldErrors();

        var validationErrorDetails = buildValidationErrorDetails(
                "Validation error",
                status.value(),
                "Validation failed for one or more fields",
                request.getDescription(false),
                fieldErrors
        );
        return new ResponseEntity<>(validationErrorDetails, headers, status);
    }

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException exception, HttpHeaders headers,
                                                                  HttpStatus status, WebRequest request) {

//        var message = exception.getCause() != null ? exception.getCause().getMessage() : "";
        var message = "Request payload not readable";
        var errorDetails = buildErrorDetails(
                message,
                status.value(),
                message,
                request.getDescription(false));
        return new ResponseEntity<>(errorDetails, headers, status);
    }

    public ErrorDetailsResponse buildErrorDetails(String error, int status, String message, String path) {
        return ErrorDetailsResponse.builder()
                .error(error)
                .httpStatusCode(status)
                .timestamp(OffsetDateTime.now())
                .message(message)
                .path(path.replace("uri=", ""))
                .build();
    }

    public ErrorDetailsResponse buildValidationErrorDetails(String error, int status, String message, String path, List<FieldError> fieldErrors) {

        Map<String, String> fieldErrorsMap = fieldErrors.stream()
                .collect(Collectors.toMap(FieldError::getField, FieldError::getDefaultMessage));

        var errorDetails = buildErrorDetails(error, status, message, path);
        errorDetails.setFieldErrors(fieldErrorsMap);

        return errorDetails;
    }
}