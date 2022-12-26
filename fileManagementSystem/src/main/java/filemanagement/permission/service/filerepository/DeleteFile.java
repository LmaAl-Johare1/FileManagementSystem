package filemanagement.permission.service.filerepository;

import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

import filemanagement.log.Loggers;
import filemanagement.exception.NoFileException;

import filemanagement.permission.IPermission;
import filemanagement.permission.service.versioncontrol.GetFile;
import filemanagement.permission.service.ReadFile;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

public class DeleteFile implements IPermission {
    private static boolean isRemoved =false;
    private static int index;
    static String fileName;

    public static void deleteFile() throws IOException {
        FileReader reader = new FileReader("./files.json");
        JSONTokener jsonString = new JSONTokener(reader);
        JSONObject jsonObject = new JSONObject(jsonString);
        JSONArray filesArray = jsonObject.getJSONArray("files");

        if (filesArray.length()==0) {
            Loggers.logWarning("No files in the system \n");
        }
        else {
            ReadFile.printFileName();
        Scanner scanner = new Scanner(System.in);
        System.out.print("\n Enter file name you want to delete (ex:file.txt): ");
        String nameWithType = scanner.nextLine();

        try {
             fileName = nameWithType.substring(0, nameWithType.lastIndexOf('.'));
        } catch (Exception e) {
            throw new NoFileException();
        }

            for (int i = 0; i < filesArray.length(); i++) {
                JSONArray innerArray = filesArray.getJSONArray(i);
                JSONObject objFile = innerArray.getJSONObject(0);
                String fileType = GetFile.getExtension(nameWithType);
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
                GetFile.updateJsonData(jsonObject);

            } else {
                throw new NoFileException();
            }
        }
    }

    @Override
    public void permission() throws IOException {
        deleteFile() ;
    }
}
