package fileManagement.service;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Scanner;
import java.util.logging.Logger;

import fileManagement.model.fileModel;
import org.json.*;

public class ImportFile {
    private static final Logger LOGGER = Logger.getLogger(ImportFile.class.getName());

    public static void ImportFile() throws IOException {
        System.out.print("\n Enter file path you want to add: ");
        Scanner scanPath = new Scanner(System.in);  // Create a Scanner object
        String filePath = scanPath.nextLine();

        fileModel file = new fileModel(); //from file model
        Path path = Paths.get(filePath); // get path
        String filename = String.valueOf(path.getFileName()); // get filename
        //make it encrypted
        byte[] fileNameBytes = filename.getBytes();
        String encryptedFileName = Base64.getEncoder().encodeToString(fileNameBytes);

        //JSONObject obj = new JSONObject();
        //  the size of the file in bytes
        String fileSize = String.valueOf(Files.size(path));
        file.setFileSize(fileSize);
        //the type of the file
        int index = filename.lastIndexOf('.');
        if (index > 0) {
            String extension = filename.substring(index + 1);
            file.setType(extension);
        }
        //the data of the file
        String data = "";
        BufferedReader reader = new BufferedReader(new FileReader(filePath));
        String Line;
        while ((Line = reader.readLine()) != null) {
            data += Line;

        }

        reader.close();
        file.setPath(path);
        file.setFileName(filename);
        file.setFileNameEncy(encryptedFileName);
        file.setFileData(data);
        ArrayList<fileModel> array = new ArrayList<fileModel>();
        array.add(new fileModel(file.getFileNameEncy() + "", file.getType() + "", Path.of(file.getPath() + ""), file.getFileSize() + "", file.getFileName() + "", file.getFileData() + ""));

        // Read existing data from files.json, if it exists
        public static final Logger LOGGER = Logger.getLogger(ImportFile.class.getName());
        public static boolean fileFound = false;
        static JSONObject jsonObject;
        public static StringBuilder fileData = new StringBuilder();
        public static void readJsonFile () {
            File jsonFile = new File("./files.json");
            if (!jsonFile.exists()) {
                LOGGER.warning("Json file doesn't exist \n");
            }
            StringBuilder sb = new StringBuilder();
            try (FileReader fr = new FileReader(jsonFile);
                 Scanner scan = new Scanner(fr)) {
                while (scan.hasNextLine()) {
                    String line = scan.nextLine();
                    sb.append(line);
                }
                jsonObject = new JSONObject(sb.toString());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        public static void updateJsonData (JSONObject jsonObject) throws IOException {
            FileWriter fw = new FileWriter("./files.json");
            fw.write(jsonObject.toString());
            fw.close();
        }
        static void readFileData (String filePath) throws IOException {
            BufferedReader reader = new BufferedReader(new FileReader(filePath));
            String line;
            while ((line = reader.readLine()) != null) {
                fileData.append(line);
            }
            reader.close();
        }
        public static String getExtension (String filename){
            int index = filename.lastIndexOf('.');
            String fileExtension = null;
            if (index > 0) {
                fileExtension = filename.substring(index + 1);
            }
            return fileExtension;
        }
    }
}




