package filemanagement.service;


import filemanagement.service.exception.JsonReadingException;
import filemanagement.service.exception.NoFileException;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;


import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;


public class ReadFile {
    public static void PrintFileName()  {

            FileReader reader = new FileReader("C:\\Users\\lmaar\\OneDrive\\Desktop\\FileManagement\\fileManagementSystem\\files.json");
            JSONTokener jsonString = new JSONTokener(reader);

            JSONObject json = new JSONObject(jsonString);
            JSONArray filesArray = json.getJSONArray("files");
            System.out.println(" List Of File in the system : ");
            for (int i = 0; i < filesArray.length(); i++) {
                int fileCount = i + 1;
                JSONArray innerArray = filesArray.getJSONArray(i);
                for (int j = 0; j < innerArray.length(); j++) {
                    JSONObject fileObject = innerArray.getJSONObject(j);
                    String fileName = fileObject.getString("fileName");
                    System.out.println("File number " + fileCount + " " + "is " + " " + fileName);
                }
            }

    }
}
