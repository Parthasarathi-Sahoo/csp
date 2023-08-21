package com.rabo.csp.exception;

import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * GlobalExceptionHandler : Global exception handler for csp.
 * When no suitable error handler found then standard Global Exception handler would take over
 * @author Parthasarathi Sahoo (partha.sahoo1994@gmail.com)
 */
@RestControllerAdvice
@Log4j2
public class GlobalExceptionHandler {

    private ErrorDetails errorMessage(Exception ex, WebRequest request) {
        List<String> errorMessages = new ArrayList<>();
        errorMessages.add(ex.getMessage());
        return errorMessage(errorMessages, request);
    }

    private ErrorDetails errorMessage(List<String> errorMessages, WebRequest request) {
        log.error(errorMessages);
        String details = getDetails(request);
        return new ErrorDetails(new Date(), errorMessages, details);
    }

    protected String getDetails(WebRequest request) {
        return request.getDescription(false);
    }

    /**
     * ioException : Method ioException takes FileProcessingException and the subsequent webRequest as input
     * and returns the error details in form of ResponseEntity
     * @param ex FileProcessingException
     * @param request WebRequest
     * @return ResponseEntity<ErrorDetails>
     */
    @ExceptionHandler(FileProcessingException.class)
    public ResponseEntity<ErrorDetails> ioException(FileProcessingException ex, WebRequest request) {
        return new ResponseEntity<>(errorMessage(ex, request), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    /**
     * ioException : Method inputValidationException takes InputValidationException and the subsequent webRequest as input
     * and returns the error details in form of ResponseEntity
     * @param ex InputValidationException
     * @param request WebRequest
     * @return ResponseEntity<ErrorDetails>
     */

    @ExceptionHandler(InputValidationException.class)
    public ResponseEntity<ErrorDetails> inputValidationException(InputValidationException ex, WebRequest request) {
        return new ResponseEntity<>(errorMessage(ex, request), HttpStatus.BAD_REQUEST);
    }

    /**
     * ioException : Method technicalException takes TechnicalException and the subsequent webRequest as input
     * and returns the error details in form of ResponseEntity
     * @param ex TechnicalException
     * @param request WebRequest
     * @return ResponseEntity<ErrorDetails>
     */
    @ExceptionHandler(ApplicationException.class)
    public ResponseEntity<ErrorDetails> technicalException(ApplicationException ex, WebRequest request) {
        return new ResponseEntity<>(errorMessage(ex, request), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    /**
     * ioException : Method globalExceptionHandler takes any Exception and the subsequent webRequest as input
     * and returns the error details in form of ResponseEntity
     * The Global Exception catch all exception when any Exception could not be handled by above mentioned Specific Exception Handler
     * @param ex Exception
     * @param request WebRequest
     * @return ResponseEntity<ErrorDetails>
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorDetails> globalExceptionHandler(Exception ex, WebRequest request) {
        log.error("Global Exception : ", ex);
        return new ResponseEntity<>(errorMessage(ex, request), HttpStatus.INTERNAL_SERVER_ERROR);
    }


}
