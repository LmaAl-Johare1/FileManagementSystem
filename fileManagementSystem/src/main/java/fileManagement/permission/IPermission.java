package filemanagement.permission;

import filemanagement.exception.*;

import java.io.FileNotFoundException;
import java.io.IOException;

public interface IPermission {
    void permission() throws IOException, NameNotFoundException, NoDataInFileJsonException;
}
