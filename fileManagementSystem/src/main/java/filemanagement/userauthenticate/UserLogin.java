package filemanagement.userauthenticate;

import filemanagement.exception.NameNotFoundException;
import filemanagement.exception.NoDataInFileJsonException;
import filemanagement.permission.model.UserModel;
import filemanagement.exception.JsonReadingException;
import filemanagement.exception.UserNotFoundException;

import filemanagement.log.Loggers;
import filemanagement.permission.service.ReadFile;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class UserLogin {

    public static String id;
    public static final int MAXIMUM_NUMBER_OF_ATTEMPTS = 3;
    public static final String LOGGER_INFO_MESSAGE = "You have exceeded the maximum number of attempts. Please contact the administrator if you have forgotten your password.";
    private static UserLogin instance;

    private UserLogin() {}
    public static synchronized UserLogin getInstance() {
        if (instance == null) {
            instance = new UserLogin();
        }
        return instance;
    }
    public static final String LOGGER_WARNING_MESSAGE = "Log-in Into System Failed";
    public static final String LOGGER_INFO_MESSAGE_SUCCESS = "Log-in Into System Successfully";
    public void logIn(ReadUserInfoFromJson reader) throws UserNotFoundException, JsonReadingException, IOException, NameNotFoundException, NoDataInFileJsonException {
        FindUserById findUserById = new FindUserById();
        Scanner scanner = new Scanner(System.in);
        List<UserModel> users = reader.getUserInfoFromJson();
        int counter = 0;

        while (true) {
            System.out.println("Enter Your id : ");
            id = scanner.nextLine();

            if (findUserById.idExistsInList(Integer.parseInt(id), users)) {
                UserModel user = findUserById.getUserById(Integer.parseInt(id), users);
                String correctPassword = user.getPassword();

                System.out.println("Enter Your Password : ");
                String enteredPassword = scanner.nextLine();
                if (enteredPassword.equals(correctPassword)) {

                    Loggers.logInfo(LOGGER_INFO_MESSAGE_SUCCESS);
                    findUserById.getTypeUserById(id);
                    break;
                } else {
                    Loggers.logWarning(LOGGER_WARNING_MESSAGE);
                    counter++;
                    if (counter == MAXIMUM_NUMBER_OF_ATTEMPTS) {
                        Loggers.logInfo(LOGGER_INFO_MESSAGE);
                        break;
                    }
                }

            } else {

                Loggers.logWarning(LOGGER_WARNING_MESSAGE);
                counter++;
                if (counter == MAXIMUM_NUMBER_OF_ATTEMPTS) {
                    Loggers.logInfo(LOGGER_INFO_MESSAGE);
                    break;
                }
            }
        }
    }
}