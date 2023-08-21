package com.rabo.csp.constants;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * ErrorMessage class maintains the standard defined error messages
 * @author Parthasarathi Sahoo (partha.sahoo1994@gmail.com)
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class ErrorMessages {

    public static final String INVALID_XML_FILE = "Invalid xml file";
    public static final String INVALID_CSV_FILE = "Invalid CSV file";
    public static final String BEAN_TO_CSV_BUILDER_EXCEPTION = "Can not convert statementOutputDto bean to csv data";
    public static final String INPUT_FILE_FORMAT_EXCEPTION = "The input file format is not acceptable. Please upload file of either CSV or XML format.";
    public static final String EMPTY_LOCAL_DATE_EXCEPTION = "The localDateTime can not be null.";
    public static final String EMPTY_DATE_FORMAT_EXCEPTION = "The dateFormatterPattern can not be null.";
    public static final String FILE_PROCESSING_EXCEPTION = "Could not process file";
}
