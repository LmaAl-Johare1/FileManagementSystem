package filemanagement.service;
import java.io.*;
import java.util.Scanner;
import filemanagement.service.exception.JsonReadingException;
import filemanagement.service.log.Logger;
import org.json.JSONArray;
import org.json.JSONObject;
public class DeleteFile extends GetFile {
    public static void deleteFile() throws IOException, JsonReadingException {
        Scanner scanner = new Scanner(System.in);
        Logger.logInfo("\n Enter file name you want to delete (ex:file.txt): ");
        String fileName = scanner.nextLine();

        // Read the JSON file and parse it into a Java object
        readJsonFile();
        JSONArray filesArray = jsonObject.getJSONArray("files");
        // Find the index of the file with the specified name
        if (filesArray.length()==0) {
            Logger.logWarning("The file doesn't exist \n");
        }
        for (int i = 0; i < filesArray.length(); i++) {
            JSONArray innerArray = filesArray.getJSONArray(i);
            for (int j = 0; j < innerArray.length(); j++) {
                JSONObject objFile = innerArray.getJSONObject(j);
                String fileNameDb = objFile.getString("fileName");
                if(!fileName.equals((fileNameDb))){
                    Logger.logError("The file doesn't exist \n");
                }
                else{
                    filesArray.remove(i);
                    Logger.logInfo("File deleted successfully \n");
                    break;
                }
            }
        }
        updateJsonData(jsonObject);
    }
}
