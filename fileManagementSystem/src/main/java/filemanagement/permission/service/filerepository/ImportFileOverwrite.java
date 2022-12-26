package filemanagement.permission.service.filerepository;

import filemanagement.exception.FileSizeException;
import filemanagement.exception.JsonReadingException;
import filemanagement.exception.NoFileException;
import filemanagement.exception.UnableToReadFile;
import filemanagement.log.Loggers;
import filemanagement.permission.IPermission;
import filemanagement.permission.service.versioncontrol.GetFile;
import filemanagement.permission.service.versioncontrol.Property;
import org.json.JSONObject;

import java.io.FileNotFoundException;
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
    public void overWrite() throws JsonReadingException {
        Path path= Property.path;
        StringBuilder fileData=Property.fileData;
        String fileSize=Property.fileSize;
        JSONObject objFile=Property.objFile;
        JSONObject jsonObject= Property.jsonObject;
        objFile.put("path", path);
        objFile.put("fileSize", fileSize);
        objFile.put("fileData", fileData.toString());
        Loggers.logInfo("The file overwrite done successfully \n");
        GetFile.updateJsonData(jsonObject);
    }

    @Override
    public void permission() throws NoFileException, UnableToReadFile, FileNotFoundException, FileSizeException, JsonReadingException {
        Property.properties();
        overWrite();
    }
}

