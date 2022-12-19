package fileManagement;

import fileManagement.service.DeleteFile;
import fileManagement.service.ImportFile;

import java.io.IOException;

public class App {
    public static void main(String[] args) throws IOException {


       ImportFile.ImportFile();
        DeleteFile.deleteFile();

    }
}
