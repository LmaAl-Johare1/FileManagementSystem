package filemanagement.service;
import java.io.*;
import java.util.Scanner;
import filemanagement.service.exception.JsonReadingException;
import filemanagement.service.exception.NoFileException;
import org.json.*;

public class GetFile {
    public static boolean fileFound = false;
     static JSONObject jsonObject;
    static String line;
    public static String fileExtension = null;
    public static StringBuilder fileData = new StringBuilder();
        public static void readJsonFile () throws NoFileException {
            File jsonFile = new File("./files.json");
            if (!jsonFile.exists()) {
                throw new NoFileException();
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
        public static void updateJsonData (JSONObject jsonObject) throws JsonReadingException {
            try {
                FileWriter fw = new FileWriter("./files.json");
                fw.write(jsonObject.toString());
                fw.close();
            }
            catch (IOException e) {
                throw new JsonReadingException(e.getMessage());
            }
        }
        static void readFileData (String filePath) throws NoFileException {
            try{
            BufferedReader reader = new BufferedReader(new FileReader(filePath));
            while ((line = reader.readLine()) != null) {
                fileData.append(line);
            }
            reader.close();
            }
            catch (IOException e) {
                throw new NoFileException();
            }
        }
        public static String getExtension (String name){
            int index = name.lastIndexOf('.');
            if (index > 0) {
                fileExtension = name.substring(index + 1);
            }
            return fileExtension;
        }
        public static String encryptName(String name){
            return name +"-file";
        }
}




