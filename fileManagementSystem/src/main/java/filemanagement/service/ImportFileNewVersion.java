package filemanagement.service;

import filemanagement.service.exception.UnableToReadFile;
import filemanagement.service.log.Loggers;
import org.json.JSONArray;
import org.json.JSONObject;

import java.nio.file.Path;


public class ImportFileNewVersion {
    public void newVersion(Path path, String filename, String nameWithType, String fileData, JSONArray filesArray, JSONObject data, String fileSize,int version) throws UnableToReadFile {
        Import.addFile(path, filename +"("+version+")", nameWithType, fileData.toString(), filesArray, data, fileSize);
        Loggers.logInfo("The file new version done successfully \n");
    }
}
