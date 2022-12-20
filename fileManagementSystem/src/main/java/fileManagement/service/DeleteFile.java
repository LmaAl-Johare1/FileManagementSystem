package fileManagement.service;
import java.io.*;
import java.util.Scanner;
import java.util.logging.Logger;
import org.json.JSONArray;
import org.json.JSONObject;
public class DeleteFile extends ImportFile {
    private static final Logger LOGGER = Logger.getLogger(DeleteFile.class.getName());
    public static void deleteFile() throws IOException {
        Scanner scanner = new Scanner(System.in);
        System.out.print("\n Enter file name you want to delete (ex:file.txt): ");
        String fileName = scanner.nextLine();

        // Read the JSON file and parse it into a Java object
        readJsonFile();
        JSONArray filesArray = jsonObject.getJSONArray("files");
        // Find the index of the file with the specified name
        for (int i = 0; i < filesArray.length(); i++) {
            JSONArray innerArray = filesArray.getJSONArray(i);
            for (int j = 0; j < innerArray.length(); j++) {
                JSONObject objFile = innerArray.getJSONObject(j);
                String fileNameDb = objFile.getString("fileName");
                if(fileName.equals((fileNameDb))){
                    filesArray.remove(i);
                    LOGGER.info("File deleted successfully \n");
                    break;
                }
            }
        }
        if (!fileFound) {
            LOGGER.warning("The file doesn't exist \n");
        }
        updateJsonData(jsonObject);
    }
}
