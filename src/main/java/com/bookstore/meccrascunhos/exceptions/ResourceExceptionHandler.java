package com.bookstore.meccrascunhos.exceptions;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.nio.file.Path;
import java.time.Instant;

@ControllerAdvice
public class ResourceExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<StandardError> resourceNotFound(ResourceNotFoundException e, HttpServletRequest request) {
        int status = HttpStatus.NOT_FOUND.value();
        String error = "NOT FOUND";
        Path path = Path.of(request.getRequestURI());
        String message = e.getMessage();

        return ResponseEntity.status(status).body(new StandardError(Instant.now(), status, error, path, message));

    }

    @ExceptionHandler(RequiredObjectIsNullException.class)
    public ResponseEntity<StandardError> badRequest(RequiredObjectIsNullException e, HttpServletRequest request) {
        int status = HttpStatus.BAD_REQUEST.value();
        String error = "BAD REQUEST";
        Path path = Path.of(request.getRequestURI());
        String message = e.getMessage();

        return ResponseEntity.status(status).body(new StandardError(Instant.now(), status, error, path, message));
    }

}
