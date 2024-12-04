import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.regex.Pattern;

public class AppUtil {
    // Shared DateTimeFormatter for consistent formatting
    public static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("HH:mm:ss dd-MM-yyyy");

    // VRN Validation Pattern
    private static final String VRN_PATTERN = "^[A-Z]{2}[0-9]{2}\\s?[A-Z]{3}$";

    /**
     * Validates if the given VRN matches the UK number plate format.
     * 
     * @param vrn Vehicle Registration Number
     * @return true if valid, false otherwise
     */
    public static boolean validateVRN(String vrn) {
        return Pattern.matches(VRN_PATTERN, vrn.toUpperCase().replaceAll("\\s+", ""));
    }

    /**
     * Standardizes the VRN to a consistent format.
     * 
     * @param vrn Vehicle Registration Number
     * @return Standardized VRN
     */
    public static String standardizeVRN(String vrn) {
        return vrn.toUpperCase().replaceAll("\\s+", "");
    }

    /**
     * Formats a LocalDateTime object into a readable string.
     * 
     * @param time LocalDateTime to format
     * @return Formatted date-time string or "N/A" if null
     */
    public static String formatDateTime(LocalDateTime time) {
        return time != null ? time.format(FORMATTER) : "N/A";
    }

    /**
     * Parses a date-time string into a LocalDateTime object.
     * 
     * @param dateTimeStr Date-time string to parse
     * @return Parsed LocalDateTime object
     */
    public static LocalDateTime parseDateTime(String dateTimeStr) {
        return LocalDateTime.parse(dateTimeStr, FORMATTER);
    }
}
