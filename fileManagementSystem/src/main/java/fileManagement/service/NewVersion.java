package fileManagement.service;
import fileManagement.model.*;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Scanner;
import java.util.logging.Logger;
import org.json.*;
public class NewVersion {
    private static final Logger LOGGER = Logger.getLogger(DeleteFile.class.getName());

    public NewVersion() throws IOException {
    }

    public static void multiVersion() throws IOException {

        Scanner scanner = new Scanner(System.in);
        System.out.print("\n Enter file path you want to  ");
        String filePath = scanner.nextLine();

        // Read the JSON file and parse it into a Java object
        File file = new File("./files.json");
        if (!file.exists()) {
            LOGGER.warning("Json file doesn't exist \n");
        }
        StringBuilder sb = new StringBuilder();
        JSONObject data;
        try (FileReader fr = new FileReader(file);
             Scanner scan = new Scanner(fr)) {
            while (scan.hasNextLine()) {
                String line = scan.nextLine();
                sb.append(line);
            }
            data = new JSONObject(sb.toString());
        }

        JSONArray filesArray = data.getJSONArray("files");
        Path path = Paths.get(filePath);
        String filename = String.valueOf(path.getFileName());
        //make it encrypted
        byte[] fileNameBytes = filename.getBytes();
        String encryptedFileName = Base64.getEncoder().encodeToString(fileNameBytes);

        //the data of the file
        String fileData = "";
        BufferedReader reader = new BufferedReader(new FileReader(filePath));
        String Line;
        while ((Line = reader.readLine()) != null) {
            fileData += Line;
        }
        reader.close();


            if (filesArray.length() == 0) {
                AddFile(path, filename, encryptedFileName, fileData, filesArray,data);
                return;
            }

        for (int i = 0; i < filesArray.length(); i++) {
            /*
            if (filesArray.length() == 0) {
                AddFile(path, filename, encryptedFileName, fileData, filesArray,data);
            }
             */
                JSONArray innerArray = filesArray.getJSONArray(i);
                for (int j = 0; j < innerArray.length(); j++) {
                    JSONObject objFile = innerArray.getJSONObject(j);
                    String fileNameDb = objFile.getString("fileName");
                    String fileTypeDb = objFile.getString("fileType");

                    if (filename.equals((fileNameDb)) && fileTypeDb.equals(GetExtension(filename))){
                        Scanner toReplace = new Scanner(System.in);
                        System.out.print("\n The file already exists do you want to overwrite? (yes/no)");
                        String replace = toReplace.nextLine().toLowerCase();
                        if (replace.equals("yes")) {
                            objFile.put("path", path);
                            String fileSize = String.valueOf(Files.size(path));
                            objFile.put("fileSize", fileSize);
                            objFile.put("fileData", fileData);
                            System.out.print("HI");
                        } else
                            System.out.print("no");
                    } else {
                        AddFile(path, filename, encryptedFileName, fileData, filesArray,data);
                        //LOGGER.info("File added successfully \n");
                    }
                }
            }

        // Write the modified data back to the file
        FileWriter fw = new FileWriter("./files.json");
        fw.write(data.toString());
        fw.close();
}


    private static void AddFile(Path path,String filename,String encryptedFileName,String fileData,JSONArray filesArray,JSONObject data) throws IOException {
        FileModel fileModel = new FileModel(); //from file model
        fileModel.setPath(path);
        fileModel.setFileName(filename);
        fileModel.setFileNameEncy(encryptedFileName);
        fileModel.setFileData(fileData);
        fileModel.setFileType(GetExtension(filename));
        ArrayList<FileModel> array = new ArrayList<>();
        array.add(new FileModel(fileModel.getFileNameEncy() + "", fileModel.getFileType() + "", Path.of(fileModel.getPath() + ""), fileModel.getFileSize() + "", fileModel.getFileName() + "", fileModel.getFileData() + ""));
        filesArray.put(array);

        try (FileWriter fw = new FileWriter("./files.json")) {
            fw.write(data.toString());
        }
    }

    private static String GetExtension(String filename) {
        int index = filename.lastIndexOf('.');
        String fileExtension = null;
        if (index > 0) {
            fileExtension = filename.substring(index + 1);
        }
        return fileExtension;
    }
}
