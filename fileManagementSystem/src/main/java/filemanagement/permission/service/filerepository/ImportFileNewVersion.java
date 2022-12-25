package filemanagement.permission.service.filerepository;

import filemanagement.exception.UnableToReadFile;
import filemanagement.log.Loggers;
import org.json.JSONArray;
import org.json.JSONObject;

import java.nio.file.Path;


public class ImportFileNewVersion {
    public void newVersion(Path path, String filename, String nameWithType, String fileData, JSONArray filesArray, JSONObject data, String fileSize,int version) throws UnableToReadFile {
        Import.addFile(path, filename +"("+version+")", nameWithType, fileData, filesArray, data, fileSize);
        Loggers.logInfo("The file new version done successfully \n");
    }
}
