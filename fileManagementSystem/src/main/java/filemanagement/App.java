package filemanagement;

import filemanagement.exception.NoDataInFileJsonException;
import filemanagement.exception.UserNotFoundException;
import filemanagement.exception.NameNotFoundException;
import filemanagement.permission.service.classification.ClassifyByType;
import filemanagement.userauthenticate.ReadUserInfoFromJson;
import filemanagement.userauthenticate.UserLogin;

import java.io.IOException;


public class App {
    public static void main(String[] args) throws IOException, NameNotFoundException, NoDataInFileJsonException, UserNotFoundException {

        ReadUserInfoFromJson readInfo = ReadUserInfoFromJson.getInstance();
        System.out.println(readInfo.getUserInfoFromJson());
        UserLogin userLogin = UserLogin.getInstance();
        userLogin.logIn(readInfo);


}
}
