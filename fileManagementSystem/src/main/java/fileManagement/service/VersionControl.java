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
public class VersionControl extends ImportFile{
    private static boolean fileFound = false;
    public static void versionControl() throws IOException {
        Scanner scanner = new Scanner(System.in);
        System.out.print("\n Enter file path you want to  ");
        String filePath = scanner.nextLine();
        // Read the JSON file and parse it into a Java object
        readJsonFile();

        JSONArray filesArray = jsonObject.getJSONArray("files");
        Path path = Paths.get(filePath);
        String fileSize = String.valueOf(Files.size(path));
        String filename = String.valueOf(path.getFileName());
        //make it encrypted
        byte[] fileNameBytes = filename.getBytes();
        String encryptedFileName = Base64.getEncoder().encodeToString(fileNameBytes);

        //the data of the file
        readFileData(filePath);
        for (int i = 0; i < filesArray.length(); i++) {
            JSONArray innerArray = filesArray.getJSONArray(i);
            for (int j = 0; j < innerArray.length(); j++) {
                JSONObject objFile = innerArray.getJSONObject(j);
                String fileNameDb = objFile.getString("fileName");
                String fileTypeDb = objFile.getString("fileType");
                String fileExt = getExtension(filename);
                if (filename.equals((fileNameDb)) && fileTypeDb.equals(fileExt)) {
                    Scanner toReplace = new Scanner(System.in);
                    System.out.print("\n Disabled Version control? (yes/no)");
                    String replace = toReplace.nextLine().toLowerCase();
                    if (replace.equals("yes")) {
                        //new version
                        objFile.put("path", path);
                        objFile.put("fileSize", fileSize);
                        objFile.put("fileData", fileData.toString());
                        fileFound = true;
                        LOGGER.info("The file overwrite done successfully \n");
                        break;
                    } else {
                        //new version
                    }
                }
            }
        }
            if (!fileFound) {
                addFile(path, filename, encryptedFileName, fileData.toString(), filesArray, jsonObject, fileSize);
            }
            updateJsonData(jsonObject);
            }

    private static void addFile(Path path, String filename, String encryptedFileName, String fileData, JSONArray filesArray, JSONObject data, String fileSize ) throws IOException {
        FileModel fileModel = new FileModel(); //from file model
        fileModel.setPath(path);
        fileModel.setFileName(filename);
        fileModel.setFileNameEncy(encryptedFileName);
        fileModel.setFileData(fileData);
        fileModel.setFileType(getExtension(filename));
        fileModel.setFileSize(fileSize);
        ArrayList<FileModel> array = new ArrayList<>();
        array.add(new FileModel(fileModel.getFileNameEncy() + "", fileModel.getFileType() + "", Path.of(fileModel.getPath() + ""), fileModel.getFileSize() + "", fileModel.getFileName() + "", fileModel.getFileData() + ""));
        filesArray.put(array);

        try (FileWriter fw = new FileWriter("./files.json")) {
            fw.write(data.toString());
        }
    }
}
