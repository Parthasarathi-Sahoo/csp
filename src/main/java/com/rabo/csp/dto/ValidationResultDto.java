package com.rabo.csp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * ValidationResultDto is used for Validation  Dto mapping
 * @author Parthasarathi Sahoo (partha.sahoo1994@gmail.com)
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ValidationResultDto {

    private Long transactionReference;
    private String description;
}
