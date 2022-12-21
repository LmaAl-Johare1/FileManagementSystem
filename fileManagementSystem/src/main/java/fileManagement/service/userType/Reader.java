package fileManagement.service.userType;

import fileManagement.service.log.logger;
import fileManagement.service.menu.IMenu;

import java.util.Scanner;

public class Reader extends User implements IMenu {


    @Override
    public void displayMenu() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("=============Reader Menu============");
        System.out.println(" * Read files : 1");
        System.out.println(" * Classify files by :");
        System.out.println("    > Type : 2 ");
        System.out.println("    > Size : 3 ");
        System.out.println("    > Custom category : 4 ");
        System.out.println("Enter number to operation : ");
        int option = Integer.parseInt(scanner.nextLine());
        selectOption(option);
    }
    @Override
    public void selectOption(int option) {
        switch (option) {
            case 1:
                System.out.println("read");
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
                logger.logError("Invalid option selected.");
                break;
        }
    }
}
