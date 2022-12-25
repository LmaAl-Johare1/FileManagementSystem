package filemanagement.usertype.users;

import filemanagement.permission.service.filerepository.ExportFile;
import filemanagement.permission.service.ReadFile;
import filemanagement.exception.JsonReadingException;

import filemanagement.log.Loggers;

import java.io.IOException;
import java.util.Scanner;

public class Admin extends User {
    private static Admin instance;

    private Admin() {}

    public static synchronized Admin getInstance() {
        if (instance == null) {
            instance = new Admin();
        }
        return instance;
    }

    @Override
    public void displayMenu() throws IOException, JsonReadingException {
        Scanner scanner = new Scanner(System.in);
        boolean isExit = false;
        while (!isExit) {
            System.out.println("=============Admin Menu============");
            System.out.println(" * Read files : 1");
            System.out.println(" * Import files with the latest version : 2");
            System.out.println(" * Import files with the new version : 3");
            System.out.println(" * Export files : 4");
            System.out.println(" * Delete files : 5");
            System.out.println(" * Classify files by :");
            System.out.println("    > Type : 6 ");
            System.out.println("    > Size : 7 ");
            System.out.println("    > Custom category by name : 8 ");
            System.out.println(" * Rollback feature : 9");
            System.out.println(" * Exit : 10");
            System.out.println("Enter number to operation : ");
            int option = Integer.parseInt(scanner.nextLine());
            if (option == 10) {
                isExit = true;
            } else {
                selectOption(option);
            }
        }
    }
    @Override
    public void selectOption(int option) throws IOException, JsonReadingException {
        switch (option) {
            case 1:
                System.out.println("Please Enter the File number you want to read : ");
                ReadFile.printFileName();
                ReadFile.printFileData();
                break;
            case 2:

                // Import files with the latest version class
                break;
            case 3:
                // Import files with overwrite
                break;
            case 4:
                System.out.println("Enter File number you want to export it please : ");
                ExportFile.exportFile();
                // Export files
                break;
            case 5:
                //delete
                break;
            case 6:
                // Classify files by Type
                break;
            case 7:
                // Classify files by Size
                break;
            case 8:
                // Classify files by Custom category
                break;
            case 9:
                // Rollback feature
                break;
            case 10:
                // Create new file
                break;
            default:

            Loggers.logError("Invalid option selected.");
                break;
        }
    }
}
