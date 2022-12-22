package filemanagement.service.menu;

import filemanagement.service.exception.NoFileException;

public interface IMenu {
    void displayMenu() throws NoFileException;
    void selectOption(int option) throws NoFileException;
}
