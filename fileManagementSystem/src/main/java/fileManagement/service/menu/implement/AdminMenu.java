package fileManagement.service.menu.implement;

import fileManagement.service.menu.interfaces.IMenu;

public class AdminMenu implements IMenu {

    @Override
    public void menu() {
        System.out.println(" * Read files : 1");
        System.out.println(" * Import files with the latest version : 2");
        System.out.println(" * Import files with overwrite : 3");
        System.out.println(" * Export files : 4");
        System.out.println(" * Delete files : 5");
        System.out.println(" * Classify files by :");
        System.out.println("    > Type : 6 ");
        System.out.println("    > Size : 7 ");
        System.out.println("    > Custom category : 8 ");
        System.out.println(" * Rollback feature : 9");
        System.out.println(" * Create new file: 10");
    }
}
