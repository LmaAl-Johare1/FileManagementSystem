package filemanagement.usertype.users;

import filemanagement.permission.service.filerepository.ExportFile;
import filemanagement.permission.service.ReadFile;

import filemanagement.log.Loggers;

import java.io.IOException;
import java.util.Scanner;

public class Stuff extends User {
    private static Stuff instance;

    private Stuff() {}
    public static synchronized Stuff getInstance() {
        if (instance == null) {
            instance = new Stuff();
        }
        return instance;
    }

    @Override
    public void displayMenu() throws IOException {
        Scanner scanner = new Scanner(System.in);
        boolean isExit = false;
        while (!isExit) {
            System.out.println("=============Stuff Menu============");
            System.out.println(" * Read files : 1");
            System.out.println(" * Import files with the new version : 2");
            System.out.println(" * Export files : 3");
            System.out.println(" * Classify files by :");
            System.out.println("    > Type : 4 ");
            System.out.println("    > Size : 5 ");
            System.out.println("    > Custom category by name : 6 ");
            System.out.println(" * Rollback feature : 7");
            System.out.println(" * Exit : 8");
            System.out.println("Enter number to operation : ");
            int option = Integer.parseInt(scanner.nextLine());
            if (option == 8) {
                isExit = true;
            } else {
                selectOption(option);
            }
        }
    }
    @Override
    public void selectOption(int option) throws IOException {
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
                System.out.println("Enter File number you want to export it please : ");
                ExportFile.exportFile();
                // Export files
                break;
            case 4:
                // Classify files by Type
                break;
            case 5:
                // Classify files by Size
                break;
            case 6:
                // Classify files by Custom category
                break;
            case 7:
                // Rollback feature
                break;
            case 8:
                // Create new file
                break;
            default:

                Loggers.logError("Invalid option selected.");
                break;
        }
    }
}
