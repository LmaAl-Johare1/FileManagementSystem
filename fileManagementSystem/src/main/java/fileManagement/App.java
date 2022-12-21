package fileManagement;

import fileManagement.service.authenticate.ReadUserInfoFromJson;
import fileManagement.service.authenticate.UserLogin;
import fileManagement.service.exception.JsonReadingException;
import fileManagement.service.exception.UserNotFoundException;

import java.io.IOException;

public class App {
    public static void main(String[] args) throws JsonReadingException, UserNotFoundException, IOException {

        ReadUserInfoFromJson reader = new ReadUserInfoFromJson();
        System.out.println(reader.getUserInfoFromJson());
        UserLogin userLogin=new UserLogin();
        userLogin.logIn(reader);

        //ClassificationByType classificationByType =new ClassificationByType();
       // classificationByType.sortUsersByName();
}
}
