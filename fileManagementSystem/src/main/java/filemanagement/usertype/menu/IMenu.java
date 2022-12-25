package filemanagement.usertype.menu;

import filemanagement.exception.JsonReadingException;

import java.io.IOException;

public interface IMenu {
    void displayMenu() throws IOException, JsonReadingException;
    void selectOption(int option) throws IOException, JsonReadingException;
}
