package filemanagement.usertype.users;

import filemanagement.exception.NameNotFoundException;
import filemanagement.exception.NoDataInFileJsonException;
import filemanagement.usertype.menu.IMenu;

import java.io.IOException;

public class User implements IMenu {
    @Override
    public void displayMenu() throws IOException, NameNotFoundException, NoDataInFileJsonException {}

    @Override
    public void selectOption(int option) throws IOException, NameNotFoundException, NoDataInFileJsonException {}
}
