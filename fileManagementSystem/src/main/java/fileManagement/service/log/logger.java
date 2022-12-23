package filemanagement.service.log;

import java.io.FileWriter;
import java.io.IOException;

public class logger {
    private static final String LOG_FILE = "log.txt";

    public static void logInfo(String message) {
        try (FileWriter writer = new FileWriter(LOG_FILE, true)) {
            writer.write("INFO" + message + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("INFO: " + message);
    }

    public static void logWarning(String message) {
        try (FileWriter writer = new FileWriter(LOG_FILE, true)) {
            writer.write("WARNING: " + message + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
        java.util.logging.Logger.getLogger("WARNING: " + message);
    }
    public static void logError(String message) {
        try (FileWriter writer = new FileWriter(LOG_FILE, true)) {
            writer.write("ERROR: " + message + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("ERROR: " + message);
    }
    public static void logDebug(String message) {
        try (FileWriter writer = new FileWriter(LOG_FILE, true)) {
            writer.write("DEBUG: " + message + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("DEBUG: " + message);
    }
}
