package filemanagement.permission.service.versioncontrol;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;

import filemanagement.exception.FileSizeException;
import filemanagement.exception.JsonReadingException;
import filemanagement.exception.NoFileException;
import org.json.*;

public class GetFile {

    public static void updateJsonData(JSONObject jsonObject) throws JsonReadingException {

        try {
            FileWriter fileWriter = new FileWriter("./files.json");
            fileWriter.write(jsonObject.toString());
            fileWriter.close();
        }
        catch (IOException e) {
            throw new JsonReadingException(e.getMessage());
        }
    }
        static StringBuilder readFileData(String filePath) throws NullPointerException, JsonReadingException, NoFileException {
            StringBuilder fileData = new StringBuilder();
            String line;
            try{
                BufferedReader reader = new BufferedReader(new FileReader(filePath));
                while ( (line=reader.readLine())!= null) {
                fileData.append(line);
            }
            }
            catch (IOException e) {
                throw new NoFileException();
            }
            return fileData;
        }
        public static String getExtension (String name){
            return name.substring(name.lastIndexOf('.') + 1);
        }
        public static String encryptName(String name){
            return name +"-file";
        }

        public static String getSize(Path path) throws NoFileException, FileSizeException {
            boolean exists = Files.exists(path);
            if (!exists) {
                throw new NoFileException();
            }
            try{
                return String.valueOf(Files.size(path));
            }catch (IOException e){
                throw new FileSizeException();
            }
        }
}




