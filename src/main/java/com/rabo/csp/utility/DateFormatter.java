package com.rabo.csp.utility;

import com.rabo.csp.constants.ErrorMessages;
import com.rabo.csp.exception.ApplicationException;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * DateFormatter : Utility class to provide date formatting capability
 * @author Parthasarathi Sahoo (partha.sahoo1994@gmail.com)
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class DateFormatter {

    /**
     * getFormattedLocalTime : Takes LocalDateTime and dateFormatterPattern as input
     * and returns LocalDateTime formatted in the input dateFormatterPattern.
     * Throws TechnicalException if one the required param is null.
     * @param localDateTime LocalDateTime
     * @param dateFormatterPattern String the desired dateFormatterPattern
     * @return String The LocalDateTime in String format
     */
    public static String getFormattedLocalTime(LocalDateTime localDateTime, String dateFormatterPattern) {

        if (localDateTime == null) {
            throw new ApplicationException(ErrorMessages.EMPTY_LOCAL_DATE_EXCEPTION);
        }
        if (dateFormatterPattern == null) {
            throw new ApplicationException(ErrorMessages.EMPTY_DATE_FORMAT_EXCEPTION);
        }

        return localDateTime.format(DateTimeFormatter.ofPattern(dateFormatterPattern));
    }

}
