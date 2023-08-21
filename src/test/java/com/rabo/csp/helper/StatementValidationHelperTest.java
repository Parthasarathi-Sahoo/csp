package com.rabo.csp.helper;

import com.rabo.csp.dto.StatementInputDto;
import com.rabo.csp.dto.StatementRecordDto;
import com.rabo.csp.dto.ValidationResultDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(MockitoExtension.class)
class StatementValidationHelperTest {

    private final StatementValidatorHelper helper = new StatementValidatorHelper();
    private final StatementInputDto input = new StatementInputDto();

    @BeforeEach
     void init() {
        List<StatementRecordDto> records = new ArrayList<>();
        records.add(new StatementRecordDto(220220L, "NL93ABNA0585619023", "Subscription from Rik de Vries", BigDecimal.ONE, BigDecimal.ZERO, BigDecimal.ONE));
        records.add(new StatementRecordDto(220220L, "NL93ABNA0666619023", "Candy for Daniel", BigDecimal.ONE, BigDecimal.ZERO, BigDecimal.ONE));
        records.add(new StatementRecordDto(169170L, "NL27SNSB0917829871", "Tickets for Disneyland", BigDecimal.ONE, BigDecimal.ZERO, BigDecimal.TEN));
        records.add(new StatementRecordDto(327769L, "NL32RABO0195610843", "Subscription from Rik de Vries", BigDecimal.ONE, BigDecimal.ZERO, BigDecimal.ONE));
        records.add(new StatementRecordDto(706090L, "NL90ABNA0585647886", "Subscription from Rik de Vries", BigDecimal.ONE, BigDecimal.ZERO, BigDecimal.ONE));

        input.setStatementRecordDtoList(records);
    }

    @Test
    void testValidate() {
        List<ValidationResultDto> expected = new ArrayList<>();
        expected.add(new ValidationResultDto(220220L, "Subscription from Rik de Vries"));
        expected.add(new ValidationResultDto(220220L, "Candy for Daniel"));
        expected.add(new ValidationResultDto(169170L, "Tickets for Disneyland"));

        List<ValidationResultDto> actual = helper.validateStatement(input);
        assertEquals(3, actual.size());
        assertTrue(actual.containsAll(expected));
        assertTrue(expected.containsAll(actual));
    }

}
