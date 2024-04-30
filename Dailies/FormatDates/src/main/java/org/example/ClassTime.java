package org.example;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

public class ClassTime {
// ai code:
    private static final ZoneId UTC_ZONE = ZoneId.of("UTC");
    private static final ZoneId CST_ZONE = ZoneId.of("America/Chicago");

    public static String formatDateTime(LocalDateTime dateTime, String pattern) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
        return dateTime.format(formatter);
    }

    public static LocalDateTime convertToCST(LocalDateTime dateTime) {
        return dateTime.atZone(UTC_ZONE).withZoneSameInstant(CST_ZONE).toLocalDateTime();
    }

    public static void main(String[] args) {
        LocalDateTime currentDateTime = LocalDateTime.now();

        // Display current date and time
        System.out.println("Current Date and Time: " + currentDateTime);

        // Format examples
        String[] patterns = {
                "MM-dd-yyyy",
                "yyyy-MM-dd",
                "MMMM dd, yyyy",
                "EEEE, MMMM dd, yyyy HH:mm",
                "hh:mm a",
                "hh:mm a 'on' dd-MMM-yyyy"
        };

        for (String pattern : patterns) {
            System.out.println("Formatted as " + pattern + ": " + formatDateTime(currentDateTime, pattern));
        }

        // Convert to CST
        LocalDateTime cstDateTime = convertToCST(currentDateTime);
        System.out.println("Current time in Central Standard Time (CST): " + cstDateTime);
    }
}