package fileManagement.service;

import fileManagement.model.*;
import java.io.FileWriter;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

public class FileService {

    public static void main(String args[])   {
        System.out.println("Enter the path of file");
        Scanner myObj = new Scanner(System.in);  // Create a Scanner object
        String path1 = myObj.nextLine();

        file File1 = new file();

        // JSONObject obj = new JSONObject();
        Path path = Paths.get(path1);
        File1.setPath(path);
        String filename = String.valueOf(path.getFileName());
        File1.setFileName(filename);
        int index = filename.lastIndexOf('.');
        if (index > 0) {
            String extension = filename.substring(index + 1);
            File1.setType(extension);

        }


        ArrayList<file> array = new ArrayList<file>();
        array.add(new file(File1.getFileName()+"", File1.getType()+"", Path.of(File1.getPath() + "")));



        try (FileWriter file = new FileWriter("newfile.json")) {
            file.write(array.toString());
            System.out.println("Successfully Copied JSON Object to File...");
        } catch (Exception e) {
            System.out.println(e);

        }
    }

}
