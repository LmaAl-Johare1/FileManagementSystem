package filemanagement.usertype.users;

import filemanagement.exception.JsonReadingException;
import filemanagement.usertype.menu.IMenu;

import java.io.IOException;

public class User implements IMenu {
    private static User instance;

    private User() {}
    public static synchronized User getInstance() {
        if (instance == null) {
            instance = new User();
        }
        return instance;
    }
    @Override
    public void displayMenu() throws IOException, JsonReadingException {}

    @Override
    public void selectOption(int option) throws IOException, JsonReadingException {}
}
