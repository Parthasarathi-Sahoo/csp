package com.rabo.csp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * StatementRecordDto is used for Statement Record Dto mapping
 * @author Parthasarathi Sahoo (partha.sahoo1994@gmail.com)
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class StatementRecordDto {

    private Long reference;
    private String accountNumber;
    private String description;
    private BigDecimal startBalance;
    private BigDecimal mutation;
    private BigDecimal endBalance;

}
