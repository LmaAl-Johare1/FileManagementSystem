package fileManagement;

import fileManagement.service.FileService;

import java.io.IOException;

public class App {
    public static void main(String[] args) throws IOException {
       FileService.ImportFile();
        //FileService.ReadFile();
    }
}
