package filemanagement.service.exception;

import java.io.IOException;

public class FileSizeException extends IOException {
    public FileSizeException(){
        super("Unable to get file size");
    }
}
