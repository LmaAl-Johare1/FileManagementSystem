package filemanagement.service;
import filemanagement.model.*;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

import filemanagement.service.exception.JsonReadingException;
import filemanagement.service.exception.NoFileException;
import filemanagement.service.exception.UnableToReadFile;
import filemanagement.service.log.logger;
import org.json.*;
public class VersionControl extends GetFile {
    private static int version = 0;
    public static void versionControl() throws IOException, JsonReadingException {
        Scanner scanner = new Scanner(System.in);
        logger.logInfo("Enter file path you want to add:");
        String filePath = scanner.nextLine();
        readJsonFile();
        JSONArray filesArray = jsonObject.getJSONArray("files");
        Path path = Paths.get(filePath);
        boolean exists = Files.exists(path);
        if (!exists) {
            throw new NoFileException();
        }
        String fileSize = String.valueOf(Files.size(path));
        String nameWithType = String.valueOf(path.getFileName());
        String filename = nameWithType.substring(0, nameWithType.lastIndexOf('.'));
        JSONObject objFile= null;
        //the data of the file
        readFileData(filePath);
        String replace;
        try {
            if (filesArray.length() == 0) {
                addFile(path, filename + version, nameWithType, fileData.toString(), filesArray, jsonObject, fileSize);
                logger.logInfo("The file added successfully \n");
            } else {
                for (int i = 0; i < filesArray.length(); i++) {
                    JSONArray innerArray = filesArray.getJSONArray(i);
                    for (int j = 0; j < innerArray.length(); j++) {
                        objFile = innerArray.getJSONObject(j);
                        if (objFile.getString("path").equals(filePath)) {
                            version++;
                        }
                    }
                }
                Scanner toReplace = new Scanner(System.in);
                logger.logInfo("\n Disabled Version control? (yes/no)");
                replace = toReplace.nextLine().toLowerCase();
                if (replace.equals("no")) {
                    addFile(path, filename + version, nameWithType, fileData.toString(), filesArray, jsonObject, fileSize);
                    logger.logInfo("The file new version done successfully \n");

                } else if (replace.equals("yes")) {
                    objFile.put("path", path);
                    objFile.put("fileSize", fileSize);
                    objFile.put("fileData", fileData.toString());
                    fileFound = true;
                    logger.logInfo("The file overwrite done successfully \n");
                } else {
                    logger.logError("wrong operation \n");
                }
            }
        }
        catch (IOException e) {
            throw new JsonReadingException(e.getMessage());
        }
    }

    private static void addFile(Path path,String filename,String name, String fileData, JSONArray filesArray, JSONObject data, String fileSize ) throws UnableToReadFile {
        FileModel fileModel = new FileModel();
        fileModel.setPath(path);
        fileModel.setFileName(filename);
        fileModel.setFileNameEncy(encryptName(name+version));
        fileModel.setFileData(fileData);
        fileModel.setFileType(getExtension(name));
        fileModel.setFileSize(fileSize);
        ArrayList<FileModel> array = new ArrayList<>();
        array.add(new FileModel(fileModel.getFileNameEncy() + "", fileModel.getFileType() + "", Path.of(fileModel.getPath() + ""), fileModel.getFileSize() + "", fileModel.getFileName() + "", fileModel.getFileData() + ""));
        filesArray.put(array);
        try (FileWriter fw = new FileWriter("./files.json")) {
            fw.write(data.toString());
        }
        catch (IOException e) {
            throw new UnableToReadFile();
        }
    }
}