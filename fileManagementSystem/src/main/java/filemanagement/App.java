package filemanagement;

import filemanagement.exception.NoDataInFileJsonException;
import filemanagement.exception.UserNotFoundException;
import filemanagement.exception.NameNotFoundException;
<<<<<<< HEAD
import filemanagement.permission.service.ReadFile;
=======
import filemanagement.permission.model.FileModel;
import filemanagement.permission.service.classification.ClassifyByName;
>>>>>>> dafddc778f87ce524d4854d785b8462191317beb
import filemanagement.permission.service.classification.ClassifyBySize;
import filemanagement.permission.service.classification.ClassifyByType;
import filemanagement.permission.service.classification.ReadFileInfoFromJson;
import filemanagement.userauthenticate.ReadUserInfoFromJson;
import filemanagement.userauthenticate.UserLogin;

import java.io.IOException;


public class App {
    public static void main(String[] args) throws Throwable {

    ReadUserInfoFromJson readInfo = ReadUserInfoFromJson.getInstance();
        System.out.println(readInfo.getUserInfoFromJson());
        UserLogin userLogin = UserLogin.getInstance();
        userLogin.logIn(readInfo);

    }
}




<<<<<<< HEAD


    }
}
=======
>>>>>>> dafddc778f87ce524d4854d785b8462191317beb
