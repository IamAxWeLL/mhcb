package com.mhcb.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.ZoneId;
import java.time.ZonedDateTime;

@ControllerAdvice
public class FormStateExceptionHandler {

    @ExceptionHandler(FormStateRequestException.class)
    public ResponseEntity<Object> handleFormStateRequestException(final FormStateRequestException e) {
        final HttpStatus badRequest = HttpStatus.BAD_REQUEST;
        final FormStateException formStateException = new FormStateException
                (
                e.getMessage(),
                ZonedDateTime.now(ZoneId.of("Z"))
        );
        return new ResponseEntity<>(formStateException, badRequest);
    }
}
