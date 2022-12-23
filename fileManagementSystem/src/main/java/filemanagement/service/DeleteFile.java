package filemanagement.service;
import java.util.Scanner;
import filemanagement.service.exception.JsonReadingException;
import filemanagement.service.exception.NoFileException;
import filemanagement.service.log.Logger;
import org.json.JSONArray;
import org.json.JSONObject;
public class DeleteFile extends GetFile {
    static boolean removed;
    static JSONArray innerArray=null;
    static int index;
    static String filename;

    public static void deleteFile() throws JsonReadingException, NoFileException {
        readJsonFile();
        JSONArray filesArray = jsonObject.getJSONArray("files");

        Scanner scanner = new Scanner(System.in);
        System.out.print("\n Enter file name you want to delete (ex:file.txt): ");
        String nameWithType = scanner.nextLine();
        try {
            String filename = nameWithType.substring(0, nameWithType.lastIndexOf('.'));
        } catch (Exception e) {
            throw new NoFileException();
        }

        // Find the index of the file with the specified name
        if (filesArray.length() == 0) {
            throw new NoFileException();
        }
        else {
            for (int i = 0; i < filesArray.length(); i++) {
                innerArray = filesArray.getJSONArray(i);
                JSONObject objFile = innerArray.getJSONObject(0);
                String fileNameDb = objFile.getString("fileName");
                String fileTypeDb = objFile.getString("fileType");
                if ((filename.equals(fileNameDb)) && (getExtension(nameWithType).equals(fileTypeDb))) {
                    removed = false;
                    index = i;
                    break;
                }
            }
            if (removed) {
                filesArray.remove(index);
                Logger.logInfo("File deleted successfully \n");
                updateJsonData(jsonObject);
            } else {
                throw new NoFileException();
            }
        }
    }
}
