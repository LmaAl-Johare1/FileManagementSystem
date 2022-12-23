package filemanagement;

import filemanagement.service.DeleteFile;
import filemanagement.service.ReadFile;
import filemanagement.service.RollBack;
import filemanagement.service.VersionControl;
import filemanagement.service.authenticate.ReadUserInfoFromJson;
import filemanagement.service.authenticate.UserLogin;
import filemanagement.service.classification.*;
import filemanagement.service.exception.JsonReadingException;
import filemanagement.service.exception.NameNotFoundException;
import filemanagement.service.exception.UserNotFoundException;

import java.io.IOException;


public class App {
    public static void main(String[] args) throws JsonReadingException, UserNotFoundException, IOException, NameNotFoundException {
       /* ReadUserInfoFromJson reader = new ReadUserInfoFromJson();
        System.out.println(reader.getUserInfoFromJson());
        UserLogin userLogin=new UserLogin();
        userLogin.logIn(reader);*/
        //VersionControl.versionControl();
        //RollBack.rollback();
        //DeleteFile.deleteFile();
        //ClassificationByType classificationByType =new ClassificationByType();
        //classificationByType.sortUsersByName();
       /* ReadFileInfoFromJson reads = new ReadFileInfoFromJson();
        System.out.println(reads.getFileInfoFromJson());
        ClassifyByType classifyByType=new ClassifyByType();
        System.out.println("Sort by Type :" +classifyByType.classify());
        System.out.println(reads.getFileInfoFromJson());
        ClassifyBySize classifyBySize = new ClassifyBySize();
        System.out.println("Sort by Size :" + classifyBySize.classify());
        System.out.println(reads.getFileInfoFromJson());
        ClassifyByCustomCategory classifyByCustomCategory= new ClassifyByCustomCategory();
        System.out.println("Sort by Custom Category :" +classifyByCustomCategory.classify());
        System.out.println(reads.getFileInfoFromJson());
        ClassifyByName classifyByName = new ClassifyByName();
        System.out.println("Sort by Name :" +classifyByName.classify());*/

}
}
