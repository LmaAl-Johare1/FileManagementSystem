package fileManagement.service.userType;

import fileManagement.service.log.logger;
import fileManagement.service.menu.IMenu;
import fileManagement.service.userType.User;
import java.util.Scanner;

public class Admin extends User implements IMenu {
    @Override
    public void displayMenu() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("=============Admin Menu============");
        System.out.println(" * Read files : 1");
        System.out.println(" * Import files with the latest version : 2");
        System.out.println(" * Export files : 3");
        System.out.println(" * Delete files : 4");
        System.out.println(" * Classify files by :");
        System.out.println("    > Type : 5 ");
        System.out.println("    > Size : 6 ");
        System.out.println("    > Custom category : 7 ");
        System.out.println(" * Rollback feature : 8");
        System.out.println(" * Create new file : 9");
        System.out.println("Enter number to operation : ");
        int option = Integer.parseInt(scanner.nextLine());
        selectOption(option);
    }

    @Override
    public void selectOption(int option) {
        switch (option) {
            case 1:
                // Read file class
                break;
            case 2:
                // Import files with the latest version class
                break;
            case 3:
                // Import files with overwrite
                break;
            case 4:
                // Export files
                break;
            case 5:
                // Delete files
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
            logger.logError("Invalid option selected.");
                break;
        }
    }
}
