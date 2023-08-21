package com.rabo.csp.dto;

import com.opencsv.bean.CsvBindByName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * CSVStatementRecordDto dto class for CSV data mapping and transfer
 * @author Parthasarathi Sahoo (partha.sahoo1994@gmail.com)
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CSVStatementRecordDto {

    @CsvBindByName
    private String reference;

    @CsvBindByName(column = "Account Number")
    private String accountNumber;

    @CsvBindByName
    private String description;

    @CsvBindByName(column = "Start Balance")
    private String startBalance;

    @CsvBindByName
    private String mutation;

    @CsvBindByName(column = "End Balance")
    private String endBalance;

}
