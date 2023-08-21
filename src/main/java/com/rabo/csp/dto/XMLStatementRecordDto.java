package com.rabo.csp.dto;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * XMLStatementRecordDto is used for XML Statement Record Dto mapping
 * @author Parthasarathi Sahoo (partha.sahoo1994@gmail.com)
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class XMLStatementRecordDto {

    @JacksonXmlProperty(isAttribute = true)
    private String reference;

    @JacksonXmlProperty
    private String accountNumber;

    @JacksonXmlProperty
    private String description;

    @JacksonXmlProperty
    private String startBalance;

    @JacksonXmlProperty
    private String mutation;

    @JacksonXmlProperty
    private String endBalance;
}
