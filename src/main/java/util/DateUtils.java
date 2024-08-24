package util;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class DateUtils {

    /**
     * Converts a date string in the format "yyyy-MM-dd" to a {@code java.sql.Date}.
     *
     * @param dateString the date string to be converted, in the format "yyyy-MM-dd"
     * @return a {@code java.sql.Date} representing the same date as the input string, with time set to midnight
     * @throws ParseException if the date string cannot be parsed into the specified format
     */
    public static Date convertStringToSqlDate(String dateString) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        java.util.Date utilDate = sdf.parse(dateString);
        return new Date(utilDate.getTime());
    }

    /**
     * Returns the current date as a {@code java.sql.Date}.
     * <p>
     * The returned {@code java.sql.Date} represents the current date, with the time set to midnight (00:00:00).
     * </p>
     *
     * @return a {@code java.sql.Date} representing the current date
     */
    public static Date getCurrentDate() {
        java.util.Date today = new java.util.Date(); // Current date and time
        return new Date(today.getTime()); // Convert to java.sql.Date with time set to midnight
    }
}
