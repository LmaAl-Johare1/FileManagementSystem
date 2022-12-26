package filemanagement.exception;

import java.io.IOException;
public class NoFileException extends IOException {
    public NoFileException() {
        super("No such file found ");
    }
}
