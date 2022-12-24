package filemanagement.service;

import filemanagement.service.log.Loggers;
import org.json.JSONObject;

import java.nio.file.Path;

public class ImportFileOverwrite {
    public void overWrite(JSONObject objFile, Path path, StringBuilder fileData, String size, boolean isFound) {
        objFile.put("path", path);
        objFile.put("fileSize", size);
        objFile.put("fileData", fileData.toString());
        isFound = true;
        Loggers.logInfo("The file overwrite done successfully \n");
    }
}
