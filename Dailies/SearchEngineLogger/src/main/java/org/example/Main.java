package org.example;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {

    }

    public static void writeLogToFile() {
        String filePath = "src/main/resources/log.txt";
      try(BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, false))) {
        writer.write("Hello, World!");
        writer.close();
        } catch (IOException e) {
          System.out.println("Sorry, cannot write to file: " + filePath);
            throw new RuntimeException(e);
        }
      }
    }

/* pseudocode
 * Test early, test often.
 * Log a message on program start
 *   use file "log.txt" in resources folder
 *   Open file
 *   ".src/main/java/resources/log.txt"
 *
 *   Read file(?)
 *   Write to file with date and time and message
 *   Write using buffered file reader
 *
 * Loop to ask user for search terms/input
 *   Prompt user to enter search term
 *   Read user's input one line at a time with scanner
 *   Parse input
 *   On each input, log message reporting search term and result
 *   If they enter 'X' as the search term then break loop
 *
 * Log a message on program end
 * Close file writer/reader/file
 * */