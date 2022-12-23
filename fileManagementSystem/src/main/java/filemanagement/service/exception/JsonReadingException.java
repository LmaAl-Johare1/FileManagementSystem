package filemanagement.service.exception;

import java.io.IOException;

public class JsonReadingException extends IOException {
    public JsonReadingException(String message) {
        super(message);
    }
}
