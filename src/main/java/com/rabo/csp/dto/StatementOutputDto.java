package com.rabo.csp.dto;

import lombok.*;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

/**
 * StatementOutputDto is used for Statement Output Dto mapping
 * @author Parthasarathi Sahoo (partha.sahoo1994@gmail.com)
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StatementOutputDto implements Serializable {

    @Serial
    private static final long serialVersionUID = -5488240789613837662L;

    private List<ValidationResultDto> validationResult;
}
