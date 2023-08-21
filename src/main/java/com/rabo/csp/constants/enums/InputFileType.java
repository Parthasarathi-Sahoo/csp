package com.rabo.csp.constants.enums;

import java.util.Arrays;
import java.util.Collections;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * InputFileType : InputFile Type enum for csp file processing
 * @author Parthasarathi Sahoo (partha.sahoo1994@gmail.com)
 */
public enum InputFileType {
    XML("application/xml"),
    CSV("text/csv"),
    INVALID("invalid");

    public static final Map<String, InputFileType> inputFileTypeMap;

    static {
        inputFileTypeMap = Collections.unmodifiableMap(Arrays.stream(InputFileType.values())
                .collect(Collectors.toMap(item -> item.mimeType, item -> item)));
    }

    private final String mimeType;

    InputFileType(String mimeType) {
        this.mimeType = mimeType;
    }

    /**
     * getType : The method take a file mime type as input and returns the system defined InputFileType
     * Returns Invalid if mime type is null
     * @param mimeType String mimetype of the file
     * @return InputFileType
     */
    public static InputFileType getType(String mimeType) {
        return inputFileTypeMap.get(mimeType) != null ? inputFileTypeMap.get(mimeType) : INVALID;
    }

}
