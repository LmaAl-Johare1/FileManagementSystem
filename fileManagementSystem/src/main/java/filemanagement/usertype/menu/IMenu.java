package filemanagement.usertype.menu;

import filemanagement.exception.JsonReadingException;
import filemanagement.exception.NameNotFoundException;
import filemanagement.exception.NoDataInFileJsonException;

import java.io.IOException;

public interface IMenu {
    void displayMenu() throws IOException, JsonReadingException, NameNotFoundException, NoDataInFileJsonException;
    void selectOption(int option) throws IOException, JsonReadingException, NameNotFoundException, NoDataInFileJsonException;
}
