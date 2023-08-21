package com.rabo.csp.processor.impl;

import com.rabo.csp.constants.ErrorMessages;
import com.rabo.csp.dto.StatementInputDto;
import com.rabo.csp.dto.StatementRecordDto;
import com.rabo.csp.dto.XMLStatementRecordDto;
import com.rabo.csp.dto.XMLStatementRecordsDto;
import com.rabo.csp.exception.FileProcessingException;
import com.rabo.csp.mappings.XMLStatementRecordToStatementRecordMapper;
import com.rabo.csp.processor.GenericFileProcessor;
import com.rabo.csp.utility.XMLMapper;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;

/**
 * XMLProcessorImplementation : XML Processor containing concrete implementation for XML file types
 * @author Parthasarathi Sahoo (partha.sahoo1994@gmail.com)
 */
@Component
public class XMLProcessorImplementation implements GenericFileProcessor {

    private final XMLStatementRecordToStatementRecordMapper mapper = Mappers.getMapper(XMLStatementRecordToStatementRecordMapper.class);

    /**
     * processFile : The file processor for the XML file type .
     * This method takes the incoming file as InputStream and then convert the InputStream to StatementRecordDto java bean,
     * Returns the StatementInputDto
     * @param inputStream  InputStream
     * @return StatementInputDto statementInputDto
     */
    @Override
    public StatementInputDto processFile(InputStream inputStream) {

        XMLStatementRecordsDto xmlStatementRecordsDto;
        try {
            xmlStatementRecordsDto = XMLMapper.getXMLMapper().readValue(inputStream, XMLStatementRecordsDto.class);
        } catch (IOException | IllegalStateException ex) {
            throw new FileProcessingException(ErrorMessages.INVALID_XML_FILE);
        }

        return convertToStatementRecordDto(xmlStatementRecordsDto);
    }

    /**
     * convertToStatementRecordDto : The method converts the csvStatementRecordDtoList to StatementInputDto
     * @param xmlStatementRecordsDto List<XMLStatementRecordDto>
     * @return statementInputDto StatementInputDto
     */
    private StatementInputDto convertToStatementRecordDto(XMLStatementRecordsDto xmlStatementRecordsDto) {

        StatementInputDto statementInputDto = new StatementInputDto();
        statementInputDto.setStatementRecordDtoList(xmlStatementRecordsDto.getXmlStatementRecordDtoList()
                .stream()
                .map(this::mapXMLToStatementRecord)
                .toList());
        return statementInputDto;
    }

    private StatementRecordDto mapXMLToStatementRecord(XMLStatementRecordDto xmlStatementRecordDto) {
        return mapper.mapXMLStatementRecordToStatementRecordDto(xmlStatementRecordDto);
    }
}
