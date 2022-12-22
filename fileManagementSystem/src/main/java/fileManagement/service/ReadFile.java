package filemanagement.service;

import filemanagement.service.exception.NoFileException;
import filemanagement.service.log.Logger;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
public class ReadFile {
    public static void PrintFileName() throws NoFileException {
        try {
            FileReader reader = new FileReader("C:\\Users\\lmaar\\OneDrive\\Desktop\\FileManagement\\fileManagementSystem\\files.json");
            JSONTokener jsonString = new JSONTokener(reader);
            JSONObject json = new JSONObject(jsonString);
            JSONArray filesArray = json.getJSONArray("files");
            if (filesArray.length() == 0) {
                Logger.logWarning("No File in the system \n");
            } else {
                System.out.println("List Of File in the system : ");
                for (int i = 0; i < filesArray.length(); i++) {
                    int fileCount = i + 1;
                    JSONArray innerArray = filesArray.getJSONArray(i);
                    for (int j = 0; j < innerArray.length(); j++) {
                        JSONObject fileObject = innerArray.getJSONObject(j);
                        String fileName = fileObject.getString("fileName");
                        System.out.println("File number " + fileCount + " " + "is " + " " + fileName + '\n');
                    }
                }
                ReadFileSelected(filesArray);

            }

        } catch (IOException e) {
            throw new NoFileException();
        }

    }

    public static void ReadFileSelected(JSONArray filesArray) {
        System.out.println("Please Select the file number you want to read : ");
        Scanner scanner = new Scanner(System.in);
        int fileNumber = scanner.nextInt();

        // Validate the input to make sure it is a valid file number
        if (fileNumber < 1 || fileNumber > filesArray.length()) {
            Logger.logWarning("No File in the system \n");
        }

        else

    {
        JSONArray innerArray = filesArray.getJSONArray(fileNumber - 1);
        JSONObject fileObject = innerArray.getJSONObject(0);
        String fileData = fileObject.getString("fileData");
        System.out.println("The File you selected contain : "+ '\n' + fileData);

    }
}
}

