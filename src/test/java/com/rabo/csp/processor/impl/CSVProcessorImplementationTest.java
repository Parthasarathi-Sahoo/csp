package com.rabo.csp.processor.impl;

import com.rabo.csp.dto.StatementInputDto;
import com.rabo.csp.exception.InputValidationException;
import com.rabo.csp.processor.GenericFileProcessor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CSVProcessorImplementationTest {
    @Test
    void processSuccess() {
        GenericFileProcessor csvProcessor = new CSVProcessorImplementation();
        StatementInputDto input = csvProcessor.processFile(getClass().getResourceAsStream("/records.csv"));

        assertEquals(10, input.getStatementRecordDtoList().size());
    }

    @Test()
    void processFailureWithWrongData() {
        GenericFileProcessor csvProcessor = new CSVProcessorImplementation();
        Assertions.assertThrows(NumberFormatException.class, () -> {
            csvProcessor.processFile(getClass().getResourceAsStream("/wrong_records.csv"));
        });
    }

    @Test
    void processFailureWithWrongFile() {
        GenericFileProcessor csvProcessor = new CSVProcessorImplementation();
        Assertions.assertThrows( InputValidationException.class, () -> {
            csvProcessor.processFile(getClass().getResourceAsStream("/invalid.csv"));
        });
    }
}
