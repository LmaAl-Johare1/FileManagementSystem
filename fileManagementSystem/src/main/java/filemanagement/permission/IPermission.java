package filemanagement.permission;

import filemanagement.exception.JsonReadingException;
import filemanagement.exception.NameNotFoundException;
import filemanagement.exception.NoDataInFileJsonException;
import filemanagement.exception.NoFileException;

public interface IPermission {
    void permission() throws NoFileException, NameNotFoundException, JsonReadingException, NoDataInFileJsonException;
}
