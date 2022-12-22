package filemanagement.service.authenticate;

import filemanagement.model.UserModel;
import filemanagement.service.exception.NoFileException;
import filemanagement.service.exception.UserNotFoundException;
import filemanagement.service.userType.Admin;
import filemanagement.service.userType.Reader;
import filemanagement.service.userType.Stuff;
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
        for (char c : chars) {
            if (Character.isDigit(c)) {
                firstDigit = c;
                break;
            }
        }
        String firstNumber = Character.toString(firstDigit);
        return firstNumber;
    }

    public void getTypeUserById(String idType) throws NoFileException {
        Reader reader = new Reader();
        Admin admin = new Admin();
        Stuff stuff = new Stuff();
        String type = getTypeFromId(idType);
            switch (type) {
                case "1":
                    System.out.println(idType);
                    reader.displayMenu();
                    break;
                case "2":
                    System.out.println(idType);
                    stuff.displayMenu();
                    break;
                case "3":
                    System.out.println(idType);
                    admin.displayMenu();
                    break;
            }
        }


    }

/*
public String getTypeFromId() {
String id = "123456";
char[] chars = id.toCharArray();
char firstDigit = ' ';
for (char c : chars) {
    if (Character.isDigit(c)) {
        firstDigit = c;
        break;
    }
}
String firstNumber = Character.toString(firstDigit);
return firstNumber;
}
 */
