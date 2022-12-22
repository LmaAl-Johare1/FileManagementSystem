package filemanagement.service.menu;

import filemanagement.service.exception.JsonReadingException;
import filemanagement.service.exception.NoFileException;

import java.io.IOException;

public interface IMenu {
    void displayMenu() throws IOException, JsonReadingException;
    void selectOption(int option) throws IOException, JsonReadingException;
}
