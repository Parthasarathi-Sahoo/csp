package com.rabo.csp.helper;

import com.rabo.csp.dto.StatementInputDto;
import com.rabo.csp.dto.StatementRecordDto;
import com.rabo.csp.dto.ValidationResultDto;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * StatementValidatorHelper : Helper class for validating the Customer Monthly Statement
 * @author Parthasarathi Sahoo (partha.sahoo1994@gmail.com)
 */
@Component
public class StatementValidatorHelper {

    /**
     * validateStatement : The method validates the Customer Statement Input which is parsed from the Customer monthly statement file.
     * The method first checks for the duplicate Statement Record item in the Input list based on reference number
     * and then validates the End balance . (End Balance must be equal to Start Balance +/- Mutation )
     * Finally return the list of the failed items . (Item having duplicate occurrence of reference number or End Balance not correct )
     * @param inputDto StatementInputDto The customer input dto which needs to be validated
     * @return List<ValidationResultDto> the failed items
     */
    public List<ValidationResultDto> validateStatement(StatementInputDto inputDto) {
        Map<Long, List<StatementRecordDto>> frequencyMap = new HashMap<>();
        List<ValidationResultDto> result = new ArrayList<>();

        for (StatementRecordDto recordDto : inputDto.getStatementRecordDtoList()) {
            Long referenceId = recordDto.getReference();
            frequencyMap.computeIfAbsent(referenceId, k -> new ArrayList<>());
            frequencyMap.get(referenceId).add(recordDto);
        }

        for (List<StatementRecordDto> recordDtoList : frequencyMap.values()) {
            if (recordDtoList.size() > 1) {
                for (StatementRecordDto recordDto : recordDtoList) {
                    result.add(new ValidationResultDto(recordDto.getReference(), recordDto.getDescription()));
                }
            } else if (recordDtoList.size() == 1) {
                StatementRecordDto recordDto = recordDtoList.get(0);
                if (!recordDto.getStartBalance().add(recordDto.getMutation()).equals(recordDto.getEndBalance())) {
                    result.add(new ValidationResultDto(recordDto.getReference(), recordDto.getDescription()));
                }
            }
        }
        return result;
    }
}
