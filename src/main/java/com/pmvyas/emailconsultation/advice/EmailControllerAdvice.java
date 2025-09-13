package com.pmvyas.emailconsultation.advice;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.MailException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

/**
 * Global Exception Handler using Spring AOP @ControllerAdvice.
 * <p>Handles all exceptions across the entire application</p>
 */

@RestControllerAdvice
public class EmailControllerAdvice {
    /**
     * Handles validation errors when form data is invalid.
     * <p>Returns field-specific error messages in JSON</p>
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationException(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();

        ex.getBindingResult().getAllErrors().forEach(error -> {
            FieldError fieldError = (FieldError) error;
            String fieldName = fieldError.getField();
            String errorMessage = fieldError.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });

        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }

    /**
     * Handles email sending failures (SMTP, authentication, connection issues).
     * Returns structured error response with timestamp
     */
    @ExceptionHandler(MailException.class)
    public ResponseEntity<ErrorDetails> handleMailException(MailException ex) {
        ErrorDetails errorDetails = new ErrorDetails();

        errorDetails.setDateTime(LocalDateTime.now());
        errorDetails.setMessage(ex.getMessage());

        return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    /**
     * Handles general runtime exceptions.
     * Catches any unexpected errors in the application
     */
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ErrorDetails> handleRuntimeException(RuntimeException ex) {
        ErrorDetails errorDetails = new ErrorDetails();

        errorDetails.setDateTime(LocalDateTime.now());
        errorDetails.setMessage(ex.getMessage());

        return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    /**
     * Handles all other exceptions not covered by specific handlers.
     * Acts as a fallback for any unhandled exceptions
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorDetails> handleGenericException(Exception ex) {
        ErrorDetails errorDetails = new ErrorDetails();

        errorDetails.setDateTime(LocalDateTime.now());
        errorDetails.setMessage("Something went wrong. Please try again later.");

        return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
