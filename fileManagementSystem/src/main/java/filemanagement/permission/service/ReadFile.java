package filemanagement.permission.service;

import filemanagement.exception.NoFileException;

import filemanagement.log.Loggers;
import filemanagement.permission.IPermission;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;


import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

    public class ReadFile implements IPermission {

        private static final String ENTER_FILE_NUMBER = "Please Enter the File number you want to read: ";
        private static final ReadFile instance = new ReadFile();
        public static String fileData;

        private ReadFile() {}

        public static synchronized ReadFile getInstance() {
            return instance;
        }

        public static JSONArray getFilesArray() throws NoFileException {
            try (FileReader reader = new FileReader("C:\\Users\\Msi\\Desktop\\finaaaaal\\FileManagementSystem\\files.json")) {
                JSONTokener jsonString = new JSONTokener(reader);
                JSONObject json = new JSONObject(jsonString);
                return json.getJSONArray("files");
            } catch (IOException e) {
                throw new NoFileException();
            }
        }


        public static void printFileName() throws NoFileException {
            JSONArray filesArray = getFilesArray();
            if (filesArray.length() == 0) {
                Loggers.logWarning("No File in the system \n");
            } else {
                System.out.println("List of File in the system:");
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
        }

        public static void printFileData() throws NoFileException {
            try {
                JSONArray filesArray = getFilesArray();
                if (filesArray.length() == 0) {
                    printFileName();
                } else {
                    Scanner scanner = new Scanner(System.in);
                    int fileNumber = scanner.nextInt();
                    if (fileNumber < 1 || fileNumber > filesArray.length()) {
                        Loggers.logWarning("No File in the system \n");
                    } else {
                        int fileCount = 1;
                        for (int i = 0; i < filesArray.length(); i++) {
                            JSONArray innerArray = filesArray.getJSONArray(i);
                            for (int j = 0; j < innerArray.length(); j++) {
                                JSONObject fileObject = innerArray.getJSONObject(j);
                                String fileName = fileObject.getString("fileName");
                                fileData = fileObject.getString("fileData");

                                if (fileNumber == fileCount) {
                                    if (fileData == null || fileData.isEmpty()) {

                                        Loggers.logWarning("The file you selected is empty");
                                    } else {
                                        System.out.println("The data for file " + fileName + " is: " + '\n' + fileData);
                                    }
                                    return;
                                }
                                fileCount++;
                            }

                        }
                    }
                }
            }
        catch(IOException e){
                throw new NoFileException();
            }
        }

    @Override
    public void permission() throws NoFileException {
        printFileName();
        System.out.println(ENTER_FILE_NUMBER);
        printFileData();
    }
}



