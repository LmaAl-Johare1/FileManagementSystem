package fileManagement.service;
import java.io.*;
import java.util.Base64;
import java.util.Scanner;
import java.util.logging.Logger;
import org.json.JSONArray;
import org.json.JSONObject;
public class DeleteFile {
    private static final Logger LOGGER = Logger.getLogger(DeleteFile.class.getName());

    public static void deleteFile() throws IOException {
        Scanner scanner = new Scanner(System.in);
        System.out.print("\n Enter file name you want to delete (ex:file.txt): ");
        String fileName = scanner.nextLine();

        // Read the JSON file and parse it into a Java object
        File file = new File("./files.json");
        if (!file.exists()) {
            LOGGER.warning("Json file doesn't exist \n");
        }
        StringBuilder sb = new StringBuilder();
        try (FileReader fr = new FileReader(file);
             Scanner scan = new Scanner(fr)) {
            while (scan.hasNextLine()) {
                String line = scan.nextLine();
                sb.append(line);
            }
        }
        JSONObject data = new JSONObject(sb.toString());
        JSONArray filesArray = data.getJSONArray("files");

        // Find the index of the file with the specified name
        for (int i = 0; i < filesArray.length(); i++) {
            JSONArray innerArray = filesArray.getJSONArray(i);
            for (int j = 0; j < innerArray.length(); j++) {
                JSONObject objFile = innerArray.getJSONObject(j);
                byte[] fileNameBytes = fileName.getBytes();
                String encryptedFileName = Base64.getEncoder().encodeToString(fileNameBytes);
                String fileNameEncy = objFile.getString("fileNameEncy");
                if (fileNameEncy.equals(encryptedFileName)) {
                    if (fileName.equals((fileNameEncy))) {
                        filesArray.remove(j);
                        LOGGER.info("File deleted successfully \n");
                        break;
                    }
                }
            }

            // Write the modified data back to the file
            FileWriter fw = new FileWriter("./files.json");
            fw.write(data.toString());
            fw.close();
        }
    }
}