package duke.parser;

import duke.exception.Exceptions;
import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Parser that parses DateTime from String to LocalDateTime object.
 */
public class DateTimeParser {
    private static final String DATETIME_FORMAT = "yyyy-MM-dd HHmm";

    /**
     * Parses String into LocalDateTime object.
     *
     * @param dateTime String in the DATETIME_FORMAT
     * @return LocalDateTime object based on the String
     * @throws Exceptions if parsing fails
     */
    public static LocalDateTime parseDateTime(String dateTime) throws Exceptions {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(DATETIME_FORMAT);
        try {
            return LocalDateTime.parse(dateTime, dateTimeFormatter);
        } catch (DateTimeException e) {
            throw new Exceptions(e.getMessage());
        }

    }

}
