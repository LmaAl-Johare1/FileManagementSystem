package filemanagement.permission.service.filerepository;

import filemanagement.exception.NoFileException;
import filemanagement.log.Loggers;
import filemanagement.permission.IPermission;
import org.json.JSONObject;

import java.nio.file.Path;

public class ImportFileOverwrite implements IPermission {
    private static ImportFileOverwrite instance;

    private ImportFileOverwrite() {}
    public static synchronized ImportFileOverwrite getInstance() {
        if (instance == null) {
            instance = new ImportFileOverwrite();
        }
        return instance;
    }
    public void overWrite(JSONObject objFile, Path path, StringBuilder fileData, String size, boolean isFound) {
        objFile.put("path", path);
        objFile.put("fileSize", size);
        objFile.put("fileData", fileData.toString());
        isFound = true;
        Loggers.logInfo("The file overwrite done successfully \n");
    }

    @Override
    public void permission() throws NoFileException {
        System.out.println("how call it, Noor");
    }
}
