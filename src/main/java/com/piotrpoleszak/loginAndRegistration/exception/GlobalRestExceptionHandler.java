package com.piotrpoleszak.loginAndRegistration.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import static java.lang.String.format;
import static java.util.Optional.ofNullable;

@Slf4j
@ControllerAdvice
public class GlobalRestExceptionHandler {

    @ExceptionHandler(CoreException.class)
    public ResponseEntity<?> handlerGameException(CoreException e, WebRequest request) {
        String message = ofNullable(e.getMessage())
                .orElse(format("GameException thrown: status %s, code %s, request %s",
                        e.getErrorCode(),
                        e.getErrorSubcode(),
                        request.getDescription(false)));
        log.error(message, e);

        return ResponseEntity
                .status(e.getErrorCode().getHttpStatus())
                .body(e.getErrorSubcode());
    }
}
