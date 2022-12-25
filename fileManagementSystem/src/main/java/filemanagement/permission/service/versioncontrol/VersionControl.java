package filemanagement.permission.service.versioncontrol;
import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

import filemanagement.exception.FileSizeException;
import filemanagement.exception.JsonReadingException;
import filemanagement.exception.UnableToReadFile;
import filemanagement.log.Loggers;
import filemanagement.exception.NoFileException;

import filemanagement.permission.IPermission;
import filemanagement.permission.service.filerepository.Import;
import filemanagement.permission.service.filerepository.ImportFileNewVersion;
import filemanagement.permission.service.filerepository.ImportFileOverwrite;
import filemanagement.permission.service.versioncontrol.GetFile;
import org.json.*;
public class VersionControl implements IPermission {
    private static int version = 0;
    static JSONObject objFile = new JSONObject();
    private static boolean fileFound = false;

    public static void versionControl() throws FileSizeException, NoFileException, UnableToReadFile, FileNotFoundException, JsonReadingException {
        GetFile importFile = new GetFile();

        FileReader reader = new FileReader("./files.json");
        JSONTokener jsonString = new JSONTokener(reader);
        JSONObject jsonObject = new JSONObject(jsonString);
        JSONArray filesArray = jsonObject.getJSONArray("files");


        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter file path you want to add:");
        String filePath = scanner.nextLine();
        Path path = Paths.get(filePath);

        String fileSize = importFile.getSize(path);
        String nameWithType = String.valueOf(path.getFileName());
        String filename = nameWithType.substring(0, nameWithType.lastIndexOf('.'));
        StringBuilder fileData = importFile.readFileData(filePath);
        String replace;

        for (int i = 0; i < filesArray.length(); i++) {
            JSONArray innerArray = filesArray.getJSONArray(i);
            for (int j = 0; j < innerArray.length(); j++) {
                objFile = innerArray.getJSONObject(j);
                if (objFile.getString("path").equals(filePath)) {
                    version++;
                    fileFound = true;
                    break;
                }
            }
        }
        if (!fileFound) {
            Import.addFile(path, filename+"("+version+")", nameWithType, fileData.toString(), filesArray, jsonObject, fileSize);
            Loggers.logInfo("The file added successfully \n");
        } else {
            Scanner toReplace = new Scanner(System.in);
            System.out.print(" Disabled Version control? (yes/no)");
            replace = toReplace.nextLine().toLowerCase();
            if (replace.equals("no")) {
                ImportFileNewVersion newVersion= ImportFileNewVersion.getInstance();
                newVersion.newVersion(path, filename , nameWithType, fileData.toString(), filesArray, jsonObject, fileSize,version);
            } else if (replace.equals("yes")) {
                ImportFileOverwrite overwrite= ImportFileOverwrite.getInstance();
                overwrite.overWrite(objFile,path,fileData,fileSize,fileFound);
                importFile.updateJsonData(jsonObject);
            }
            else {
                Loggers.logError("wrong operation \n");
            }
        }
    }

    @Override
    public void permission() throws NoFileException {
        System.out.println("how call it Noor");
    }
}