package filemanagement.exception;

import java.io.IOException;

public class UnableToReadFile extends IOException {
    public UnableToReadFile(){
        super("Unable to read from file");
    }
}
