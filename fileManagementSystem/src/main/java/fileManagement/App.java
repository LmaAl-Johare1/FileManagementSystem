package fileManagement;

import fileManagement.service.DeleteFile;
import fileManagement.service.FileService;

import java.io.IOException;

public class App {
    public static void main(String[] args) throws IOException {
       FileService.ImportFile();
        DeleteFile.deleteFile();

    }
}
