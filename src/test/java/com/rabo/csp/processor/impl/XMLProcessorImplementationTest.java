package com.rabo.csp.processor.impl;

import com.rabo.csp.dto.StatementInputDto;
import com.rabo.csp.processor.GenericFileProcessor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

 class XMLProcessorImplementationTest {

    @Test
    void processSuccess() {
        GenericFileProcessor xmlProcessor = new XMLProcessorImplementation();
        StatementInputDto input = xmlProcessor.processFile(getClass().getResourceAsStream("/records.xml"));

        assertEquals(10, input.getStatementRecordDtoList().size());
        assertEquals(Long.valueOf("187997"), input.getStatementRecordDtoList().get(0).getReference());
        assertEquals("NL91RABO0315273637", input.getStatementRecordDtoList().get(0).getAccountNumber());
        assertEquals("Clothes for Rik King", input.getStatementRecordDtoList().get(0).getDescription());
        assertEquals(BigDecimal.valueOf(57.6), input.getStatementRecordDtoList().get(0).getStartBalance());
        assertEquals(BigDecimal.valueOf(-32.98), input.getStatementRecordDtoList().get(0).getMutation());
        assertEquals(BigDecimal.valueOf(24.62), input.getStatementRecordDtoList().get(0).getEndBalance());
    }

    @Test()
    void processFailureWithWrongData() {
        GenericFileProcessor xmlProcessor = new XMLProcessorImplementation();
        Assertions.assertThrows(NumberFormatException.class, () -> {
            xmlProcessor.processFile(getClass().getResourceAsStream("/wrong_records.xml"));
        });
    }

    @Test()
    void processFailureWithWrongFile() {
        GenericFileProcessor xmlProcessor = new XMLProcessorImplementation();
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            xmlProcessor.processFile(getClass().getResourceAsStream("/invalid.xml"));
        });
    }
}
