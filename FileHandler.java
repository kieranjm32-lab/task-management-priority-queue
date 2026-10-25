/**
 * FileHandler.java
 * -------------------------------------------------------------
 * OOP Concept: File Persistence & Exception Handling
 * Logs cleared/executed tasks permanently into a text file.
 */

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class FileHandler {
    private static final String FILE_NAME = "task_log.txt";

    /**
     * Appends an executed task log entry to task_log.txt.
     */
    public static boolean logExecution(String logEntry) {
        try (FileWriter fw = new FileWriter(FILE_NAME, true);
             PrintWriter pw = new PrintWriter(fw)) {
            pw.println(logEntry);
            return true;
        } catch (IOException e) {
            System.err.println("Error writing to log file: " + e.getMessage());
            return false;
        }
    }

    /**
     * Clears or initializes the log file.
     */
    public static boolean clearLogFile() {
        try (FileWriter fw = new FileWriter(FILE_NAME, false)) {
            fw.write("=== TASK MANAGEMENT SYSTEM EXECUTION LOG ===\n");
            return true;
        } catch (IOException e) {
            System.err.println("Error resetting log file: " + e.getMessage());
            return false;
        }
    }
}
