package filemanagement.permission.service.filerepository;

import filemanagement.exception.NoFileException;
import filemanagement.exception.UnableToReadFile;
import filemanagement.log.Loggers;
import filemanagement.permission.IPermission;
import filemanagement.permission.service.ReadFile;
import org.json.JSONArray;
import org.json.JSONObject;

import java.nio.file.Path;


public class ImportFileNewVersion implements IPermission {
    private static ImportFileNewVersion instance;

    private ImportFileNewVersion() {}
    public static synchronized ImportFileNewVersion getInstance() {
        if (instance == null) {
            instance = new ImportFileNewVersion();
        }
        return instance;
    }

    public void newVersion(Path path, String filename, String nameWithType, String fileData, JSONArray filesArray, JSONObject data, String fileSize,int version) throws UnableToReadFile {
        Import.addFile(path, filename +"("+version+")", nameWithType, fileData, filesArray, data, fileSize);
        Loggers.logInfo("The file new version done successfully \n");
    }

    @Override
    public void permission() throws NoFileException {
        System.out.println("how call it Noor");
    }
}
