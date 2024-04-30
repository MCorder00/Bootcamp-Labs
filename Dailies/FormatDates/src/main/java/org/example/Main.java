package org.example;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;
import java.util.TimeZone;
import java.util.Date;

public class Main {
    public static void main(String[] args) {

        // get local time and date
        LocalDateTime presentDateTime = LocalDateTime.now();
        System.out.println(presentDateTime);

        // Format as MM-dd-yyyy
        DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern("MM-dd-yyyy");
        System.out.println(presentDateTime.format(formatter1));

        // Format as yyyy-MM-dd
        DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        System.out.println(presentDateTime.format(formatter2));

        // Format as Month dd, yyyy
        DateTimeFormatter formatter3 = DateTimeFormatter.ofPattern("MMMM dd, yyyy");
        System.out.println(presentDateTime.format(formatter3));

        // Format as Day, Mon dd, yyyy hh:mm in GMT
        LocalDateTime presentDateTimeUTC = LocalDateTime.now(ZoneId.of("UTC"));
        DateTimeFormatter formatterGMT = DateTimeFormatter.ofPattern("EEEE, MMM dd, yyyy HH:mm");
        System.out.println("Current time in Greenwich Mean Time (GMT): " + presentDateTimeUTC.format(formatterGMT));

        // Format as hh:mm on dd-Mon-yyyy
        DateTimeFormatter formatter4 = DateTimeFormatter.ofPattern("hh:mm 'on' dd-MMM-yyyy");
        System.out.println(presentDateTime.format(formatter4));
    }



    }


/* Get current date and time
* Display current date and time
* Format as MM-dd-yyyy
* Format as yyyy-MM-dd
* Format as Month dd, yyyy
* Format as Day, Mon dd, yyyy hh:mm
* Convert universal time to central standard time
* Display time in 12 hour format
* Format as hh:mm AM/PM on dd-Mon-yyyy
* */