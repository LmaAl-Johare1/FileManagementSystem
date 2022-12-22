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
                System.out.println("List of File in the system : ");
                for (int i = 0; i < filesArray.length(); i++) {
                    int fileCount = i + 1;
                    JSONArray innerArray = filesArray.getJSONArray(i);
                    for (int j = 0; j < innerArray.length(); j++) {
                        JSONObject fileObject = innerArray.getJSONObject(j);
                        String fileName = fileObject.getString("fileName");
                        String fileType = fileObject.getString("fileType");

                        System.out.println("File number " + fileCount + " " + "is " + " " + fileName + "." + fileType + '\n');
                    }
                }

            }

        } catch (IOException e) {
            throw new NoFileException();
        }

    }


    public static void printFileData() throws NoFileException {

        try {
            FileReader reader = new FileReader("C:\\Users\\lmaar\\OneDrive\\Desktop\\FileManagement\\fileManagementSystem\\files.json");
            JSONTokener jsonString = new JSONTokener(reader);
            JSONObject json = new JSONObject(jsonString);
            JSONArray filesArray = json.getJSONArray("files");
            if (filesArray.length() == 0) {
                ReadFile.PrintFileName();
            } else {
                System.out.println("Please Enter the File number you want to read : ");
                Scanner scanner = new Scanner(System.in);
                int fileNumber = scanner.nextInt();
                if (fileNumber < 1 || fileNumber > filesArray.length()) {
                    Logger.logWarning("No File in the system \n");
                }
                int fileCount = 1;
                for (int i = 0; i < filesArray.length(); i++) {
                    JSONArray innerArray = filesArray.getJSONArray(i);
                    for (int j = 0; j < innerArray.length(); j++) {
                        JSONObject fileObject = innerArray.getJSONObject(j);
                        String fileName = fileObject.getString("fileName");
                        String fileData = fileObject.getString("fileData");

                        if (fileNumber == fileCount) {
                            if (fileData == null || fileData.isEmpty()) {
                                Logger.logWarning("The file you selected is empty");
                            } else {
                                System.out.println("The data for file " + fileName + " is: " + '\n'+ fileData);
                            }
                            return;
                        }
                        fileCount++;
                    }

                }
            }
        }
        catch(IOException e){
                throw new NoFileException();
            }
        }
    }



