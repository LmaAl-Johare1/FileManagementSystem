package filemanagement.usertype.users;

import filemanagement.permission.service.ReadFile;
import filemanagement.exception.NoFileException;
import filemanagement.log.Loggers;

import java.util.Scanner;

public class Reader extends User {

    private static Reader instance;

    private Reader() {}
    public static synchronized Reader getInstance() {
        if (instance == null) {
            instance = new Reader();
        }
        return instance;
    }
    @Override
    public void displayMenu() throws NoFileException {
        Scanner scanner = new Scanner(System.in);
        boolean isExit = false;
        while (!isExit) {
            System.out.println("=============Reader Menu============");
            System.out.println(" * Read files : 1");
            System.out.println(" * Classify files by :");
            System.out.println("    > Type : 2 ");
            System.out.println("    > Size : 3 ");
            System.out.println("    > Custom category by name : 4 ");
            System.out.println(" * Exit : 5");
            System.out.println("Enter number to operation : ");
            int option = Integer.parseInt(scanner.nextLine());
            if (option == 5) {
                isExit = true;
            } else {
                selectOption(option);
            }
        }
    }
    @Override
    public void selectOption(int option) throws NoFileException {
        switch (option) {
            case 1:
ReadFile.printFileName();
ReadFile.printFileData();
// Read file class
                break;
            case 2:
                // Classify files by Type
                break;
            case 3:
                // Classify files by Size
                break;
            case 4:
                // Classify files by Custom category
                break;
            default:
        Loggers.logError("Invalid option selected.");
                break;
        }
    }
}
