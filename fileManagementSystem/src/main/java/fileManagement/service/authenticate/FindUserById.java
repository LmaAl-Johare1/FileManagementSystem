package fileManagement.service.authenticate;

import fileManagement.model.UserModel;
import fileManagement.service.exception.UserNotFoundException;
import fileManagement.service.userType.Admin;
import fileManagement.service.userType.Reader;
import fileManagement.service.userType.Stuff;
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

    public void getTypeUserById(int id, List<UserModel> users){
        Reader reader = new Reader();
        Admin admin = new Admin();
        Stuff stuff = new Stuff();
        for (UserModel user : users) {
            char firstNumber = Integer.toString(user.getId()).charAt(0);
            switch (firstNumber) {
                case '1':
                    System.out.println(firstNumber);
                    reader.displayMenu();
                    break;
                case '2':
                    System.out.println(firstNumber);
                    stuff.displayMenu();
                    break;
                case '3':
                    System.out.println(firstNumber);
                    admin.displayMenu();
                    break;
            }
        }

    }

}
