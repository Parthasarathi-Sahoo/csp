package com.rabo.csp.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.io.Serial;

/**
 * ApplicationException : Application exception handler
 * @author Parthasarathi Sahoo (partha.sahoo1994@gmail.com)
 */
@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
public class ApplicationException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = -3151285212036036356L;

    public ApplicationException(String message) {
        super(message);
    }
}
