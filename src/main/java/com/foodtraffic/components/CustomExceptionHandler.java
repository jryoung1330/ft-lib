package com.foodtraffic.components;

import com.foodtraffic.model.response.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;

@Component
@ControllerAdvice
public class CustomExceptionHandler {

    @ExceptionHandler(ResponseStatusException.class)
    public final ResponseEntity<ErrorResponse> handleGeneralResponseExceptions(ResponseStatusException e, WebRequest request) {
        ErrorResponse res = new ErrorResponse();
        res.setError(e.getStatus().getReasonPhrase());
        res.setMessage(e.getReason());
        res.setStatus(e.getStatus().value());
        res.setPath(((ServletWebRequest) request).getRequest().getRequestURI());
        return new ResponseEntity<>(res, e.getStatus());
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public final ResponseEntity<ErrorResponse> handleValidationExceptions(ConstraintViolationException e, WebRequest request) {
        ErrorResponse res = new ErrorResponse();
        res.setError(HttpStatus.BAD_REQUEST.getReasonPhrase());
        res.setMessage(getConstraintViolations(e));
        res.setStatus(HttpStatus.BAD_REQUEST.value());
        res.setPath(((ServletWebRequest) request).getRequest().getRequestURI());
        return new ResponseEntity<>(res, HttpStatus.BAD_REQUEST);
    }

    private String getConstraintViolations(ConstraintViolationException e) {
        StringBuilder violations = new StringBuilder();
        for(ConstraintViolation<?> cv : e.getConstraintViolations()) {
            violations.append(cv.getMessage()).append('\n');
        }
        return violations.toString();
    }
}
