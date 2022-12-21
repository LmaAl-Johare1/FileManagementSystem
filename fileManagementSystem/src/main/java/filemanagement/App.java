package filemanagement;

import filemanagement.service.exception.JsonReadingException;
import filemanagement.service.exception.UserNotFoundException;

import java.io.IOException;

import static filemanagement.service.VersionControl.versionControl;

public class App {
    public static void main(String[] args) throws JsonReadingException, UserNotFoundException, IOException {
/*
        ReadUserInfoFromJson reader = new ReadUserInfoFromJson();
        System.out.println(reader.getUserInfoFromJson());
        UserLogin userLogin=new UserLogin();
        userLogin.logIn(reader);
*/
        versionControl();
        //ClassificationByType classificationByType =new ClassificationByType();
       // classificationByType.sortUsersByName();
}
}
