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
             innerArray = filesArray.getJSONArray(i);
            JSONObject objFile = innerArray.getJSONObject(0);
            String fileNameDb = objFile.getString("fileName");
            String fileTypeDb = objFile.getString("fileType");
                if ((filename.equals(fileNameDb))&& (getExtension(nameWithType).equals(fileTypeDb)) ) {
                    removed=!isRemoved();
                    break;
                }
            }
        if(removed){
            innerArray.remove(0);
            Logger.logInfo("File deleted successfully \n");
           updateJsonData(jsonObject);
        }
        else
            Logger.logError("File doesn't exist \n");

    }
        private static boolean isRemoved() {
            return false;
        }
}
