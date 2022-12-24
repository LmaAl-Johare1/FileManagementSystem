package filemanagement.service;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;

import filemanagement.service.exception.FileSizeException;
import filemanagement.service.exception.JsonReadingException;
import filemanagement.service.exception.NoFileException;
import org.json.*;

public class GetFile {

    public static void updateJsonData(JSONObject jsonObject) throws JsonReadingException {
        try {
            FileWriter fw = new FileWriter("./files.json");
            fw.write(jsonObject.toString());
            fw.close();
        }
        catch (IOException e) {
            throw new JsonReadingException(e.getMessage());
        }
    }
        static StringBuilder readFileData(String filePath) throws NoFileException,NullPointerException {
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




