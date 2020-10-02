package duke.parser;

import duke.exception.Exceptions;

import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateTimeParser {
    private static final String DATETIME_FORMAT = "yyyy-MM-dd HHmm";


    public static LocalDateTime parseDateTime(String dateTime) throws Exceptions {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(DATETIME_FORMAT);
        try {
            return LocalDateTime.parse(dateTime, dateTimeFormatter);
        } catch (DateTimeException ignored) {
        }
        throw new Exceptions("Error: Parse DateTime fail");
    }

}
