package filemanagement.usertype.menu;

import filemanagement.exception.NameNotFoundException;
import filemanagement.exception.NoDataInFileJsonException;

import java.io.IOException;

public interface IMenu {
    void displayMenu() throws IOException, NameNotFoundException, NoDataInFileJsonException;
    void selectOption(int option) throws IOException, NameNotFoundException, NoDataInFileJsonException;
}
