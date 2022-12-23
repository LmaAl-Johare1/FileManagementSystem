package filemanagement.service;

import java.util.Scanner;

import filemanagement.service.exception.JsonReadingException;
import filemanagement.service.exception.NoFileException;
import filemanagement.service.log.Loggers;

import org.json.JSONArray;
import org.json.JSONObject;
public class DeleteFile extends GetFile {
    private static boolean removed=false;
    private static int index;
    static String fileName;

    public static void deleteFile() throws JsonReadingException, NoFileException {
        readJsonFile();
        JSONArray filesArray = jsonObject.getJSONArray("files");

        Scanner scanner = new Scanner(System.in);
        System.out.print("\n Enter file name you want to delete (ex:file.txt): ");
        String nameWithType = scanner.nextLine();

        try {
             fileName = nameWithType.substring(0, nameWithType.lastIndexOf('.'));
        } catch (Exception e) {
            throw new NoFileException();
        }

        // Find the index of the file with the specified name
        if (filesArray.length() == 0) {
            throw new NoFileException();
        }
        else {
            for (int i = 0; i < filesArray.length(); i++) {
                JSONArray innerArray = filesArray.getJSONArray(i);
                JSONObject objFile = innerArray.getJSONObject(0);
                String fileNameDb = objFile.getString("fileName");
                String fileTypeDb = objFile.getString("fileType");
                if ((fileName.equals(fileNameDb)) && (getExtension(nameWithType).equals(fileTypeDb))) {
                    removed = true;
                    index = i;
                    break;
                }
            }
            if (removed) {
                filesArray.remove(index);
                Loggers.logInfo("File deleted successfully \n");
                updateJsonData(jsonObject);
            } else {
                throw new NoFileException();
            }
        }
    }
}
