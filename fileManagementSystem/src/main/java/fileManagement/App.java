package fileManagement;
import fileManagement.service.DeleteFile;
import fileManagement.service.ImportFile;

import java.io.IOException;

import static fileManagement.service.DeleteFile.deleteFile;
//import static fileManagement.service.VersionControl.versionControl;

public class App {
    public static void main(String[] args) throws IOException {
      ImportFile.ImportFile();
       DeleteFile.deleteFile();
        //versionControl();
        deleteFile();
    }
}
