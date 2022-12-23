package filemanagement.service.log;

import java.io.FileWriter;
import java.io.IOException;


import java.util.logging.*;
public class Loggers {
    public static final Logger logger = Logger.getLogger(Loggers.class.getName());
    private static final String LOG_FILE = "log.txt";

    public static void logInfo(String message) {
        try (FileWriter writer = new FileWriter(LOG_FILE, true)) {
            writer.write("INFO" + message + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
        logger.info( message);
    }

    public static void logWarning(String message) {
        try (FileWriter writer = new FileWriter(LOG_FILE, true)) {
            writer.write("WARNING: " + message + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
        logger.warning( message);
    }
    public static void logError(String message) {
        try (FileWriter writer = new FileWriter(LOG_FILE, true)) {
            writer.write("ERROR: " + message + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
        //logger.error(message);
        System.out.println("ERROR: " + message);
    }
}
