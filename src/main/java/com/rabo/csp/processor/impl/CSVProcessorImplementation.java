package com.rabo.csp.processor.impl;

import com.opencsv.CSVWriter;
import com.opencsv.ICSVWriter;
import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import com.rabo.csp.constants.ErrorMessages;
import com.rabo.csp.dto.*;
import com.rabo.csp.exception.InputValidationException;
import com.rabo.csp.exception.ApplicationException;
import com.rabo.csp.mappings.CSVStatementRecordToStatementRecordMapper;
import com.rabo.csp.processor.GenericFileProcessor;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.List;

/**
 * CSVProcessorImplementation : CSV Processor containing concrete implementation for CSV file types
 * @author Parthasarathi Sahoo (partha.sahoo1994@gmail.com)
 */
@Component
public class CSVProcessorImplementation implements GenericFileProcessor {

    private final CSVStatementRecordToStatementRecordMapper mapper = Mappers.getMapper(CSVStatementRecordToStatementRecordMapper.class);

    /**
     * processFile : The file processor for the CSV file type .
     * This method takes the incoming file as InputStream and then convert the InputStream to StatementRecordDto java bean,
     * Returns the StatementInputDto
     * @param inputStream  InputStream
     * @return StatementInputDto statementInputDto
     */
    @Override
    public StatementInputDto processFile(InputStream inputStream) {
        List<CSVStatementRecordDto> csvStatementRecordDtoList;
        try {
            csvStatementRecordDtoList = new CsvToBeanBuilder<CSVStatementRecordDto>(new BufferedReader(new InputStreamReader(inputStream)))
                    .withOrderedResults(false)
                    .withType(CSVStatementRecordDto.class)
                    .build()
                    .parse();
        } catch (NumberFormatException | IllegalStateException | NullPointerException ex) {
            throw new InputValidationException(ErrorMessages.INVALID_CSV_FILE);
        }
        return convertToStatementRecordDto(csvStatementRecordDtoList);
    }

    /**
     * convertToStatementRecordDto : The method converts the csvStatementRecordDtoList to StatementInputDto
     * @param csvStatementRecordDtoList List<CSVStatementRecordDto>
     * @return statementInputDto StatementInputDto
     */
    private StatementInputDto convertToStatementRecordDto(List<CSVStatementRecordDto> csvStatementRecordDtoList) {

        StatementInputDto statementInputDto = new StatementInputDto();
        statementInputDto.setStatementRecordDtoList(csvStatementRecordDtoList
                .stream()
                .map(this::mapCSVToStatementRecord)
                .toList());
        return statementInputDto;
    }

    private StatementRecordDto mapCSVToStatementRecord(CSVStatementRecordDto csvStatementRecordDto) {
        return mapper.mapCSVStatementRecordToStatementRecordDto(csvStatementRecordDto);
    }

    /**
     * convertStatementOutputDtoToCSV : Converts the StatementOutputDto to byte array.
     * Takes statementOutputDto as input argument and then convert the input bean to byte array content
     * @param statementOutputDto StatementOutputDto
     * @return byte[] byte array of the csv contents
     */
    public byte[] convertStatementOutputDtoToCSV(StatementOutputDto statementOutputDto) {

        try {
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            OutputStreamWriter streamWriter = new OutputStreamWriter(stream, StandardCharsets.UTF_8);
            CSVWriter writer = new CSVWriter(streamWriter);

            StatefulBeanToCsv<ValidationResultDto> statefulBeanToCsv = new StatefulBeanToCsvBuilder<ValidationResultDto>(writer)
                    .withQuotechar(ICSVWriter.DEFAULT_QUOTE_CHARACTER)
                    .withSeparator(ICSVWriter.DEFAULT_SEPARATOR)
                    .build();
            statefulBeanToCsv.write(statementOutputDto.getValidationResult());
            streamWriter.flush();

            return stream.toByteArray();
        } catch (Exception exception) {
            throw new ApplicationException(ErrorMessages.BEAN_TO_CSV_BUILDER_EXCEPTION);
        }
    }


}
