package filemanagement;

import filemanagement.service.ReadFile;
import filemanagement.service.authenticate.ReadUserInfoFromJson;
import filemanagement.service.authenticate.UserLogin;
import filemanagement.service.exception.JsonReadingException;
import filemanagement.service.exception.UserNotFoundException;

import java.io.IOException;


public class App {
    public static void main(String[] args) throws JsonReadingException, UserNotFoundException, IOException {
     /*   ReadUserInfoFromJson reader = new ReadUserInfoFromJson();
        System.out.println(reader.getUserInfoFromJson());
        UserLogin userLogin=new UserLogin();
        userLogin.logIn(reader);*/
       ReadFile.PrintFileName();
     //  ReadFile.printFileData();
    // VersionControl.versionControl();
     // DeleteFile.deleteFile();


             //ClassificationByType classificationByType =new ClassificationByType();
        // classificationByType.sortUsersByName();
}
}
