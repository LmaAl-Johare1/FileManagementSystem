package fileManagement;

<<<<<<< HEAD
import fileManagement.service.OverWrite;
=======
import fileManagement.service.DeleteFile;
import fileManagement.service.ImportFile;
>>>>>>> 6a1b79d63281b5835e977e102f2f6f9444338614

import java.io.IOException;

public class App {
    public static void main(String[] args) throws IOException {
<<<<<<< HEAD
       //FileService.ImportFile();
       //DeleteFile.deleteFile();
       OverWrite.multiVersion();
=======
       ImportFile.ImportFile();
        DeleteFile.deleteFile();

>>>>>>> 6a1b79d63281b5835e977e102f2f6f9444338614
    }
}
