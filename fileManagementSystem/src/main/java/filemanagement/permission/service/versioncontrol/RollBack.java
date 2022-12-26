package filemanagement.permission.service.versioncontrol;
import filemanagement.exception.JsonReadingException;
import filemanagement.exception.NoFileException;
import filemanagement.log.Loggers;
import filemanagement.permission.IPermission;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

public class RollBack implements IPermission {
     static int maxVersion = -1;
    public static void rollback() throws JsonReadingException, NoFileException, FileNotFoundException {
        Scanner scanner = new Scanner(System.in);
        System.out.print("\n Enter file name you want to Roll the version back (ex:file.txt): ");
        String nameWithType = scanner.nextLine();
        String filename = nameWithType.substring(0, nameWithType.lastIndexOf('.'));
        String fileType = GetFile.getExtension(nameWithType);

        FileReader reader = new FileReader("./files.json");
        JSONTokener jsonString = new JSONTokener(reader);
        JSONObject jsonObject = new JSONObject(jsonString);
        JSONArray filesArray = jsonObject.getJSONArray("files");


        if (filesArray == null) {
            throw new NoFileException();
        }

        int lastVersion=findLastVersion(filesArray, filename, fileType);

        if (lastVersion != -1) {
            for (int i = 0; i < filesArray.length(); i++) {
                JSONArray innerArray = filesArray.getJSONArray(i);
                for (int j = 0; j < innerArray.length(); j++) {
                    JSONObject objFile = innerArray.getJSONObject(j);
                    String fileNameDb = objFile.getString("fileName");
                    String fileTypeDb = objFile.getString("fileType");
                    if (fileNameDb.startsWith(filename) && fileType.equals(fileTypeDb) && fileNameDb.contains("("+maxVersion+")")) {
                        filesArray.remove(i);
                        Loggers.logInfo("The file has been rolled back \n");

                        GetFile.updateJsonData(jsonObject);
                    }
                }
            }
        } else {
            throw new NoFileException();
        }
    }
    static int findLastVersion(JSONArray filesArray, String filename, String fileType) {
        int maxVersion = 0;
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
        return maxVersion;
    }
    @Override
    public void permission() throws NoFileException, FileNotFoundException, JsonReadingException {
        rollback();
    }
}
