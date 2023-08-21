package com.rabo.csp.processor;

import com.rabo.csp.dto.StatementInputDto;
import org.springframework.stereotype.Component;

import java.io.InputStream;

/**
 * GenericFileProcessor : GenericFileProcessor interface to process CSV and XML file types
 * @author Parthasarathi Sahoo (partha.sahoo1994@gmail.com)
 */
@Component
public interface GenericFileProcessor {
    /**
     * processFile : Process file from the provided input stream .
     * The input stream of either CSV or XML File format
     * @param inputStream InputStream which is to be processed
     * @return StatementInputDto the processed inputStream in form of StatementInputDto
     */
    StatementInputDto processFile(InputStream inputStream);

}
