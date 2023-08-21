package com.rabo.csp.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 * StatementProcessorService : Interface for Statement Processor Service
 * @author Parthasarathi Sahoo (partha.sahoo1994@gmail.com)
 */
@Service
public interface StatementProcessorService {

    /**
     * processCustomerStatement : The service method for processing customer monthly statement.
     * The monthly statement is to be a MultiPartFile and should be in CSV or XML.
     * Returns the failed items in byte array
     * @param inputFile MultipartFile which needs to be validated
     * @return byte[] the validation result of the failed items
     */
    byte[] processCustomerStatement(MultipartFile inputFile);
}
