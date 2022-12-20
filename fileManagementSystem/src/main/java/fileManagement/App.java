package fileManagement;

import fileManagement.service.OverWrite;
import fileManagement.service.DeleteFile;
import fileManagement.service.ImportFile;
import java.io.IOException;

public class App {
    public static void main(String[] args) throws IOException {

       //FileService.ImportFile();
       //DeleteFile.deleteFile();
       OverWrite.multiVersion();

       ImportFile.ImportFile();
       DeleteFile.deleteFile();

       /*
       // to login
       ReadUserInfoFromJson reader = new ReadUserInfoFromJson();
        List<UserModel> users = reader.getUserInfoFromJson();
        System.out.println(reader.getUserInfoFromJson());
        UserLogin userLogin=new UserLogin();
        userLogin.logIn(reader);
        */


    }
}
