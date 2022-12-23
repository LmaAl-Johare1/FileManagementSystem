package filemanagement.service;
import java.util.Scanner;
import filemanagement.service.exception.JsonReadingException;
import filemanagement.service.exception.NoFileException;
import filemanagement.service.log.Logger;
import org.json.JSONArray;
import org.json.JSONObject;
public class DeleteFile extends GetFile {
    static boolean removed=false;
    public static void deleteFile() throws JsonReadingException, NoFileException {
        Scanner scanner = new Scanner(System.in);
        System.out.print("\n Enter file name you want to delete (ex:file.txt): ");
        String nameWithType = scanner.nextLine();
        String filename = nameWithType.substring(0, nameWithType.lastIndexOf('.'));
        // Read the JSON file and parse it into a Java object
        readJsonFile();
        JSONArray filesArray = jsonObject.getJSONArray("files");
        // Find the index of the file with the specified name
        if (filesArray.length()==0) {
            throw new NoFileException();
        }
        for (int i = 0; i < filesArray.length(); i++) {
            JSONArray innerArray = filesArray.getJSONArray(i);
            JSONObject objFile = innerArray.getJSONObject(0);
            String fileNameDb = objFile.getString("fileName");
            String fileTypeDb = objFile.getString("fileType");
            if ((filename.equals(fileNameDb))&& (getExtension(nameWithType).equals(fileTypeDb)) ) {
                innerArray.remove(0);
                isRemoved();
                Logger.logInfo("File deleted successfully \n");
                break;
            }
            }
        if(!isRemoved()){
            Logger.logError("File doesn't exist \n");
        }
        updateJsonData(jsonObject);
    }

    private static boolean isRemoved() {
         removed = true;
         return true;
    }
}