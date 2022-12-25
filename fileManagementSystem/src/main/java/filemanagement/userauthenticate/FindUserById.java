package filemanagement.userauthenticate;

import filemanagement.permission.model.UserModel;
import filemanagement.exception.JsonReadingException;
import filemanagement.exception.UserNotFoundException;
import filemanagement.usertype.users.Admin;
import filemanagement.usertype.users.Reader;
import filemanagement.usertype.users.Stuff;
import filemanagement.usertype.users.User;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

public class FindUserById {

    public boolean idExistsInList(int id, List<UserModel> users){
        for (UserModel user : users) {
            if (Objects.equals(user.getId(), id)) {
                return true;
            }
        }
        return  false;
    }
    public UserModel getUserById(int id, List<UserModel> users) throws UserNotFoundException {
        for (UserModel user : users) {
            if (Objects.equals(user.getId(), id)) {
                return user;
            }
        }
        throw new UserNotFoundException(id);
    }
    public String getTypeFromId(String id) {
        char[] chars =id.toCharArray();
        char firstDigit = ' ';
        for (char character : chars) {
            if (Character.isDigit(character)) {
                firstDigit = character;
                break;
            }
        }
        String firstNumber = Character.toString(firstDigit);
        return firstNumber;
    }

    public void getTypeUserById(String idType) throws IOException, JsonReadingException {
        User reader = Reader.getInstance();
        User admin = Admin.getInstance();
        User stuff = Stuff.getInstance();
        String type = getTypeFromId(idType);
            switch (type) {
                case "1":
                    reader.displayMenu();
                    break;
                case "2":
                    stuff.displayMenu();
                    break;
                case "3":
                    admin.displayMenu();
                    break;
            }
        }


    }

