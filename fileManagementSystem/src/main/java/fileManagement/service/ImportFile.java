package fileManagement.service;
import fileManagement.model.*;
import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;
import java.util.logging.Logger;
import org.json.*;

public class ImportFile {
    public static final Logger LOGGER = Logger.getLogger(ImportFile.class.getName());
     static JSONObject jsonObject;
    public static StringBuilder fileData = new StringBuilder();
    public static void readJsonFile() {
        File jsonFile = new File("./files.json");
        if (!jsonFile.exists()) {
            LOGGER.warning("Json file doesn't exist \n");
        }
        StringBuilder sb = new StringBuilder();
        try (FileReader fr = new FileReader(jsonFile);
             Scanner sc = new Scanner(fr)) {
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                sb.append(line);
            }
            jsonObject = new JSONObject(sb.toString());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void updateJsonData(JSONObject jsonObject) throws IOException {
        FileWriter fw = new FileWriter("./files.json");
        fw.write(jsonObject.toString());
        fw.close();
    }
    static void readFileData(String filePath) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(filePath));
        String line;
        while ((line = reader.readLine()) != null) {
            fileData.append(line);
        }
        reader.close();
    }
    public static String getExtension(String filename) {
        int index = filename.lastIndexOf('.');
        String fileExtension = null;
        if (index > 0) {
            fileExtension = filename.substring(index + 1);
        }
        return fileExtension;
    }
}




