package filemanagement.service;
import filemanagement.service.exception.*;
import filemanagement.service.log.Loggers;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

public class RollBack {
     static int maxVersion = -1;
    public static void rollback() throws JsonReadingException, NoFileException, FileNotFoundException {
        Scanner scanner = new Scanner(System.in);
        System.out.print("\n Enter file name you want to Roll the version back (ex:file.txt): ");
        String nameWithType = scanner.nextLine();
        String filename = nameWithType.substring(0, nameWithType.lastIndexOf('.'));
        GetFile roll= new GetFile();
        String fileType = roll.getExtension(nameWithType);
        // Read the JSON file and parse it into a Java object

        FileReader reader = new FileReader("./files.json");
        JSONTokener jsonString = new JSONTokener(reader);
        JSONObject jsonObject = new JSONObject(jsonString);
        JSONArray filesArray = jsonObject.getJSONArray("files");

        // If the file was not found, throw an exception
        if (filesArray == null) {
            throw new NoFileException();
        }
        // Find the file in the JSON array and get the highest version number
        int maxVersion = -1;
        for (int i = 0; i < filesArray.length(); i++) {
            JSONArray innerArray = filesArray.getJSONArray(i);
            for (int j = 0; j < innerArray.length(); j++) {
                JSONObject objFile = innerArray.getJSONObject(j);
                String fileNameDb = objFile.getString("fileName");
                String fileTypeDb = objFile.getString("fileType");
                if (fileNameDb.startsWith(filename) && fileType.equals(fileTypeDb)) {
                    int version = Integer.parseInt(fileNameDb.substring(fileNameDb.indexOf('(') + 1, fileNameDb.indexOf(')')));
                    if (version > maxVersion) {
                        maxVersion = version;
                    }
                }
            }
        }
        // If a file with the specified name and type was found, delete the file with the highest version number
        if (maxVersion != -1) {
            for (int i = 0; i < filesArray.length(); i++) {
                JSONArray innerArray = filesArray.getJSONArray(i);
                for (int j = 0; j < innerArray.length(); j++) {
                    JSONObject objFile = innerArray.getJSONObject(j);
                    String fileNameDb = objFile.getString("fileName");
                    String fileTypeDb = objFile.getString("fileType");
                    if (fileNameDb.startsWith(filename) && fileType.equals(fileTypeDb) && fileNameDb.contains("("+maxVersion+")")) {
                        // Remove the file with the highest version from the array
                        filesArray.remove(i);
                        Loggers.logInfo("The file has been rolled back \n");
                        // Update the JSON file with the updated array
                        roll.updateJsonData(jsonObject);
                    }
                }
            }
        } else {
            throw new NoFileException();
        }
    }
}
