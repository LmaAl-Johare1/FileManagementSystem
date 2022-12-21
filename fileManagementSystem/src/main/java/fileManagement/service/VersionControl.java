package fileManagement.service;
import fileManagement.model.*;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Scanner;
import org.json.*;
public class VersionControl extends GetFile {

    public static void versionControl() throws IOException {
        int version = 1;
        Scanner scanner = new Scanner(System.in);
        System.out.print("\n Enter file path you want to add: ");
        String filePath = scanner.nextLine();
        // Read the JSON file and parse it into a Java object
        readJsonFile();

        JSONArray filesArray = jsonObject.getJSONArray("files");
        Path path = Paths.get(filePath);
        String fileSize = String.valueOf(Files.size(path));
        String nameWithType = String.valueOf(path.getFileName());
        String filename = nameWithType.substring(0, nameWithType.lastIndexOf('.'));
        String encryptedFileName=encryptName(filename);

        //the data of the file
        readFileData(filePath);
        for (int i = 0; i < filesArray.length(); i++) {
            JSONArray innerArray = filesArray.getJSONArray(i);
            for (int j = 0; j < innerArray.length(); j++) {
                JSONObject objFile = innerArray.getJSONObject(j);
                String fileNameDb = objFile.getString("fileName");
                String fileTypeDb = objFile.getString("fileType");
                String fileExt = getExtension(nameWithType);
                if (filename.equals((fileNameDb)) && fileTypeDb.equals(fileExt)) {
                    version++;
                    Scanner toReplace = new Scanner(System.in);
                    System.out.print("\n Disabled Version control? (yes/no)");
                    String replace = toReplace.nextLine().toLowerCase();
                    if (replace.equals("yes")) {
                        objFile.put("path", path);
                        objFile.put("fileSize", fileSize);
                        objFile.put("fileData", fileData.toString());
                        fileFound = true;
                        LOGGER.info("The file overwrite done successfully \n");
                        break;
                    } else if(replace.equals("no")){
                        String incrementedFilename=filename+version;
                        String encryptedNewVersion=encryptName(incrementedFilename);
                        addFile(path, incrementedFilename,nameWithType, encryptedNewVersion, fileData.toString(), filesArray, jsonObject, fileSize);
                        return;
                    }
                    LOGGER.warning("wrong operation \n");
                }
            }
        }
            if (!fileFound) {
                addFile(path, filename,nameWithType, encryptedFileName, fileData.toString(), filesArray, jsonObject, fileSize);
                LOGGER.info("The file added successfully \n");
            }
            updateJsonData(jsonObject);
            }

    private static void addFile(Path path, String filename,String name, String encryptedFileName, String fileData, JSONArray filesArray, JSONObject data, String fileSize ) throws IOException {
        FileModel fileModel = new FileModel(); //from file model
        fileModel.setPath(path);
        fileModel.setFileName(filename);
        fileModel.setFileNameEncy(encryptedFileName);
        fileModel.setFileData(fileData);
        fileModel.setFileType(getExtension(name));
        fileModel.setFileSize(fileSize);
        ArrayList<FileModel> array = new ArrayList<>();
        array.add(new FileModel(fileModel.getFileNameEncy() + "", fileModel.getFileType() + "", Path.of(fileModel.getPath() + ""), fileModel.getFileSize() + "", fileModel.getFileName() + "", fileModel.getFileData() + ""));
        filesArray.put(array);
        try (FileWriter fw = new FileWriter("./files.json")) {
            fw.write(data.toString());
        }
    }
}
