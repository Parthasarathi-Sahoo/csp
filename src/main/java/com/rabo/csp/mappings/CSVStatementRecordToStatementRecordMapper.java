package com.rabo.csp.mappings;

import com.rabo.csp.dto.CSVStatementRecordDto;
import com.rabo.csp.dto.StatementRecordDto;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

/**
 * CSVStatementRecordToStatementRecordMapper : Mapper to map CSV Statement Record
 * to Statement Record
 * @author Parthasarathi Sahoo (partha.sahoo1994@gmail.com)
 */
@Mapper(componentModel = "spring")
@Component
public interface CSVStatementRecordToStatementRecordMapper {
    /**
     * mapCSVStatementRecordToStatementRecordDto: Map CSVStatementRecord DTO to StatementRecord DTO
     * @param csvStatementRecordDto CSVStatementRecordDto which needs to be mapped to the StatementRecord DTO
     * @return StatementRecordDto
     */
    StatementRecordDto mapCSVStatementRecordToStatementRecordDto(CSVStatementRecordDto csvStatementRecordDto);
}
