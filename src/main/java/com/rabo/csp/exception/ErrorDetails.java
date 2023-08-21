package com.rabo.csp.exception;

import lombok.Data;
import lombok.Getter;

import java.util.Date;
import java.util.List;

/**
 * ErrorDetails : Standard Error Details for csp
 * @author Parthasarathi Sahoo (partha.sahoo1994@gmail.com)
 */
@Data
@Getter
public class ErrorDetails {
    private Date timeStamp;
    private List<String> message;
    private String details;

    public ErrorDetails(Date timeStamp, List<String> message, String details) {
        super();
        this.timeStamp = timeStamp;
        this.message = message;
        this.details = details;
    }
}
