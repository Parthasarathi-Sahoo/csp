package com.rabo.csp.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.io.Serial;

/**
 * InputValidationException : Input validation exception handler
 * @author Parthasarathi Sahoo (partha.sahoo1994@gmail.com)
 */
@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class InputValidationException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = -6219247455776314920L;

    public InputValidationException(String message) {
        super(message);
    }
}
