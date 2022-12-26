package filemanagement.permission.service.versioncontrol;
import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

import filemanagement.exception.*;

import org.json.*;
public class Property  {
    public static int version = 0;
    public static JSONObject objFile = new JSONObject();
    public static boolean fileFound = false;
    public static String filePath;
    public static Path path;
    public static String fileSize;
    public static String nameWithType;
    public static String filename;
    public static StringBuilder fileData;
    public static JSONArray filesArray;
    public static JSONObject jsonObject;

    public static void properties() throws FileSizeException, NoFileException, FileNotFoundException{

        FileReader reader = new FileReader("./files.json");
        JSONTokener jsonString = new JSONTokener(reader);
         jsonObject = new JSONObject(jsonString);
         filesArray = jsonObject.getJSONArray("files");


        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter file path you want to add:");

         filePath = scanner.nextLine();
         path = Paths.get(filePath);
         fileSize = GetFile.getSize(path);
         nameWithType = String.valueOf(path.getFileName());
         filename = nameWithType.substring(0, nameWithType.lastIndexOf('.'));
         fileData = GetFile.readFileData(filePath);
         version=getVersion( filePath,  filesArray, version);
    }
    public static int getVersion(String filePath, JSONArray filesArray,int version) {
        for (int i = 0; i < filesArray.length(); i++) {
            JSONArray innerArray = filesArray.getJSONArray(i);
            for (int j = 0; j < innerArray.length(); j++) {
                 objFile = innerArray.getJSONObject(j);
                if (objFile.getString("path").equals(filePath)) {
                    version++;
                    fileFound = true;
                }
            }
        }
        return version;
    }
}