package filemanagement.service;

import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

import filemanagement.service.exception.JsonReadingException;

import filemanagement.service.exception.NoFileException;
import filemanagement.service.log.Loggers;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

public class DeleteFile {
    private static boolean isRemoved =false;
    private static int index;
    static String fileName;

    public static void deleteFile() throws IOException {
        GetFile delete= new GetFile();
        // Read the JSON file and parse it into a Java object
        FileReader reader = new FileReader("./files.json");
        JSONTokener jsonString = new JSONTokener(reader);
        JSONObject jsonObject = new JSONObject(jsonString);
        JSONArray filesArray = jsonObject.getJSONArray("files");

        if (filesArray.length()==0) {
            Loggers.logWarning("No files in the system \n");
        }
        else {
            ReadFile.PrintFileName();
        Scanner scanner = new Scanner(System.in);
        System.out.print("\n Enter file name you want to delete (ex:file.txt): ");
        String nameWithType = scanner.nextLine();

        try {
             fileName = nameWithType.substring(0, nameWithType.lastIndexOf('.'));
        } catch (Exception e) {
            throw new NoFileException();
        }

        // Find the index of the file with the specified name
            for (int i = 0; i < filesArray.length(); i++) {
                JSONArray innerArray = filesArray.getJSONArray(i);
                JSONObject objFile = innerArray.getJSONObject(0);
                String fileType = delete.getExtension(nameWithType);
                String fileNameDb = objFile.getString("fileName");
                String fileTypeDb = objFile.getString("fileType");
                if ((fileName.equals(fileNameDb)) && (fileType.equals(fileTypeDb))) {
                    isRemoved = true;
                    index = i;
                    break;
                }
            }
            if (isRemoved) {
                filesArray.remove(index);
                Loggers.logInfo("File deleted successfully \n");
                delete.updateJsonData(jsonObject);

            } else {
                throw new NoFileException();
            }
        }
    }
}
