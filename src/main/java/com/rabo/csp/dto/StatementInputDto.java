package com.rabo.csp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * StatementInputDto is used for Statement Input Dto mapping
 * @author Parthasarathi Sahoo (partha.sahoo1994@gmail.com)
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class StatementInputDto {

    private List<StatementRecordDto> statementRecordDtoList;

}
