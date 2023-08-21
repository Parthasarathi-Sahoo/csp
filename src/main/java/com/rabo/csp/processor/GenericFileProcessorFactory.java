package com.rabo.csp.processor;

import com.rabo.csp.processor.impl.XMLProcessorImplementation;
import com.rabo.csp.constants.enums.InputFileType;
import com.rabo.csp.processor.impl.CSVProcessorImplementation;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * GenericFileProcessorFactory : A Factory method to provide processor implementation
 * based on the file type
 * @author Parthasarathi Sahoo (partha.sahoo1994@gmail.com)
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class GenericFileProcessorFactory {

    /**
     * getFileProcessor : Factory method which takes InputFileType as method argument
     * and returns the concrete implementation method based on the InputFileType
     * Returns null if there is no available implementation for the  InputFileType
     * @param inputFileType InputFileType
     * @return GenericFileProcessor
     */
    public static GenericFileProcessor getFileProcessor(InputFileType inputFileType) {
        switch (inputFileType) {
            case CSV -> {return new CSVProcessorImplementation();}
            case XML -> {return new XMLProcessorImplementation();}
            default -> {return null;}
        }
    }
}
