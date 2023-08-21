package com.rabo.csp.service.impl;

import com.rabo.csp.constants.ErrorMessages;
import com.rabo.csp.constants.enums.InputFileType;
import com.rabo.csp.dto.StatementInputDto;
import com.rabo.csp.dto.StatementOutputDto;
import com.rabo.csp.exception.FileProcessingException;
import com.rabo.csp.exception.InputValidationException;
import com.rabo.csp.helper.StatementValidatorHelper;
import com.rabo.csp.processor.GenericFileProcessor;
import com.rabo.csp.processor.GenericFileProcessorFactory;
import com.rabo.csp.processor.impl.CSVProcessorImplementation;
import com.rabo.csp.service.StatementProcessorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;


/**
 * StatementProcessorServiceImplementation : Contains concrete implementation for Statement Processor service
 * @author Parthasarathi Sahoo (partha.sahoo1994@gmail.com)
 */
@Service
@RequiredArgsConstructor
public class StatementProcessorServiceImplementation implements StatementProcessorService {

    private final StatementValidatorHelper statementValidatorHelper;
    private final CSVProcessorImplementation csvProcessor;

    /**
     * processCustomerStatement : The service method for processing customer monthly statement.
     * The monthly statement is to be a MultiPartFile and should be in CSV or XML.
     * Returns the failed items in byte array
     * @param inputFile MultipartFile which needs to be validated
     * @return byte[] the validation result of the failed items
     */
    @Override
    public byte[] processCustomerStatement(MultipartFile inputFile) {
        StatementInputDto statementInputDto = Optional.of(inputFile)
                .map(MultipartFile::getContentType)
                .map(InputFileType::getType)
                .map(GenericFileProcessorFactory::getFileProcessor)
                .map(genericFileProcessor -> getStatementInputDto(inputFile, genericFileProcessor))
                .orElseThrow(() -> new InputValidationException(ErrorMessages.INPUT_FILE_FORMAT_EXCEPTION));

        StatementOutputDto statementOutputDto = new StatementOutputDto();
        statementOutputDto.setValidationResult(statementValidatorHelper.validateStatement(statementInputDto));

        return csvProcessor.convertStatementOutputDtoToCSV(statementOutputDto);
    }

    private StatementInputDto getStatementInputDto(MultipartFile inputFile, GenericFileProcessor genericFileProcessor) {
        try {
            return genericFileProcessor.processFile(inputFile.getInputStream());
        } catch (IOException ioException) {
            throw new FileProcessingException(ErrorMessages.FILE_PROCESSING_EXCEPTION);
        }
    }

}
