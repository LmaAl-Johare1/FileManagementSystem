package fileManagement.service.menu.implement;

import fileManagement.service.menu.interfaces.IMenu;

public class ReaderMenu implements IMenu {

    @Override
    public void menu() {
        System.out.println(" * Read files : 1");
        System.out.println(" * Classify files by :");
        System.out.println("    > Type : 2 ");
        System.out.println("    > Size : 3 ");
        System.out.println("    > Custom category : 4 ");

    }
}
