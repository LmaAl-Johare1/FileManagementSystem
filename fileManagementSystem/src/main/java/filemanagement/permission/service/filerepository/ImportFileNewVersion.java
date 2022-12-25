package filemanagement.permission.service.filerepository;

import filemanagement.exception.FileSizeException;
import filemanagement.exception.JsonReadingException;
import filemanagement.exception.NoFileException;
import filemanagement.exception.UnableToReadFile;
import filemanagement.log.Loggers;
import filemanagement.permission.IPermission;
import org.json.JSONArray;
import org.json.JSONObject;
import filemanagement.permission.service.versioncontrol.Property;

import java.io.FileNotFoundException;
import java.nio.file.Path;


public class ImportFileNewVersion implements IPermission {
    private static ImportFileNewVersion instance;

    public ImportFileNewVersion() throws UnableToReadFile, FileNotFoundException, JsonReadingException, FileSizeException, NoFileException {}
    public static synchronized ImportFileNewVersion getInstance() throws UnableToReadFile, FileNotFoundException, JsonReadingException, FileSizeException, NoFileException {
        if (instance == null) {
            instance = new ImportFileNewVersion();
        }
        return instance;
    }

    public void newVersion() throws UnableToReadFile, FileNotFoundException, JsonReadingException, FileSizeException, NoFileException {
        Path path=Property.path;
        String filename=Property.filename;
        String nameWithType=Property.nameWithType;
        StringBuilder fileData=Property.fileData;
        JSONArray filesArray=Property.filesArray;
        JSONObject data=Property.jsonObject;
        String fileSize=Property.fileSize;
        int version=Property.version;
        boolean fileFound=Property.fileFound;
        JSONObject jsonObject=Property.jsonObject;

        if (!fileFound) {
            AddFile.addFile(path, filename+"("+version+")", nameWithType, fileData.toString(), filesArray, jsonObject, fileSize);
            Loggers.logInfo("The file added successfully \n");
        }
        else {
            AddFile.addFile(path, filename+"("+version+")", nameWithType, String.valueOf(fileData), filesArray, data, fileSize);
            Loggers.logInfo("The file new version done successfully \n");
        }
    }

    @Override
    public void permission() throws NoFileException, UnableToReadFile, FileNotFoundException, FileSizeException, JsonReadingException {
        Property.properties();
        newVersion();
    }
}
