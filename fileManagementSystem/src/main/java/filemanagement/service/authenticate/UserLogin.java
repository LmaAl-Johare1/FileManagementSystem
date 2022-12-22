package filemanagement.service.authenticate;

import filemanagement.model.UserModel;
import filemanagement.service.exception.JsonReadingException;
import filemanagement.service.exception.NoFileException;
import filemanagement.service.exception.UserNotFoundException;
import filemanagement.service.log.Logger;

import java.util.List;
import java.util.Scanner;

public class UserLogin {
    public static String id;
    public static final int MAXIMUM_NUMBER_OF_ATTEMPTS = 3;
    public static final String LOGGER_INFO_MESSAGE = "You have exceeded the maximum number of attempts. Please contact the administrator if you have forgotten your password.";

    public static final String LOGGER_WARNING_MESSAGE = "Log-in Into System Failed";

    public void logIn(ReadUserInfoFromJson reader) throws UserNotFoundException, JsonReadingException, NoFileException {
        FindUserById findUserById = new FindUserById();
        Scanner scanner = new Scanner(System.in);
        List<UserModel> users = reader.getUserInfoFromJson();
        int counter = 0;

        while (true) {
            System.out.println("Enter Your id : ");
            id = scanner.nextLine();

            if (findUserById.idExistsInList(Integer.parseInt(id), users)) {
                // retrieve the correct password for the user
                UserModel user = findUserById.getUserById(Integer.parseInt(id), users);
                String correctPassword = user.getPassword();

                System.out.println("Enter Your Password : ");
                String enteredPassword = scanner.nextLine();

                // compare the entered password with the correct password
                if (enteredPassword.equals(correctPassword)) {
                    Logger.logInfo("Log-in Into System Successfully");
                    findUserById.getTypeUserById(id);
                    break;
                } else {
                    Logger.logWarning(LOGGER_WARNING_MESSAGE);
                    counter++;
                    if (counter == MAXIMUM_NUMBER_OF_ATTEMPTS) {
                        Logger.logInfo(LOGGER_INFO_MESSAGE);
                        break;
                    }
                }

            } else {
                Logger.logWarning(LOGGER_WARNING_MESSAGE);
                counter++;
                if (counter == MAXIMUM_NUMBER_OF_ATTEMPTS) {
                    Logger.logInfo(LOGGER_INFO_MESSAGE);
                    break;
                }
            }
        }
    }
}