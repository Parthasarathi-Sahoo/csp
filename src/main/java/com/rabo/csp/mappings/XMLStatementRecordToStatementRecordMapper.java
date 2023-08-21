package com.rabo.csp.mappings;

import com.rabo.csp.dto.StatementRecordDto;
import com.rabo.csp.dto.XMLStatementRecordDto;
import org.mapstruct.Mapper;

/**
 * XMLStatementRecordToStatementRecordMapper :  Mapper to map XML Statement Record
 * to Statement Record
 * @author Parthasarathi Sahoo (partha.sahoo1994@gmail.com)
 */
@Mapper(componentModel = "spring")
public interface XMLStatementRecordToStatementRecordMapper {
    /**
     * mapXMLStatementRecordToStatementRecordDto: Map XMLStatementRecordDto DTO to StatementRecord DTO
     * @param xmlStatementRecordDto XMLStatementRecordDto which needs to be mapped to the StatementRecord DTO
     * @return StatementRecordDto
     */
    StatementRecordDto mapXMLStatementRecordToStatementRecordDto(XMLStatementRecordDto xmlStatementRecordDto);
}
