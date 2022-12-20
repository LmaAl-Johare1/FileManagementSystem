package fileManagement.service.userService;

import fileManagement.model.UserModel;
import fileManagement.service.exception.JsonReadingException;
import fileManagement.service.exception.UserNotFoundException;
import fileManagement.service.log.logger;

import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class UserLogin {
    public static String id;
    public static final int MAXIMUM_NUMBER_OF_ATTEMPTS = 3;
    public static final String LOGGER_INFO_MESSAGE = "You have exceeded the maximum number of attempts. Please contact the administrator if you have forgotten your password.";

    public static final String LOGGER_WARNING_MESSAGE = "Log-in Into System Failed";
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
    public void logIn(ReadUserInfoFromJson reader) throws UserNotFoundException, JsonReadingException {
        Scanner scanner = new Scanner(System.in);
        List<UserModel> users = reader.getUserInfoFromJson();
        int counter = 0;

        while (true) {
            System.out.println("Enter Your id : ");
            id = scanner.nextLine();

            if (idExistsInList(Integer.parseInt(id), users)) {
                // retrieve the correct password for the user
                UserModel user = getUserById(Integer.parseInt(id), users);
                String correctPassword = user.getPassword();

                System.out.println("Enter Your Password : ");
                String enteredPassword = scanner.nextLine();

                // compare the entered password with the correct password
                if (enteredPassword.equals(correctPassword)) {
                    logger.logInfo("Log-in Into System Successfully");
                    break;
                } else {
                    logger.logWarning(LOGGER_WARNING_MESSAGE);
                    counter++;
                    if (counter == MAXIMUM_NUMBER_OF_ATTEMPTS) {
                        logger.logInfo(LOGGER_INFO_MESSAGE);
                        break;
                    }
                }
            } else {
                logger.logWarning(LOGGER_WARNING_MESSAGE);
                counter++;
                if (counter == MAXIMUM_NUMBER_OF_ATTEMPTS) {
                    logger.logInfo(LOGGER_INFO_MESSAGE);
                    break;
                }
            }
        }
    }
}
