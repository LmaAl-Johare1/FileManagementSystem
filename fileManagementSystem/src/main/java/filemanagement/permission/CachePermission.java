package filemanagement.permission;

import filemanagement.exception.FileSizeException;
import filemanagement.exception.JsonReadingException;
import filemanagement.exception.NoFileException;
import filemanagement.exception.UnableToReadFile;
import filemanagement.permission.service.ReadFile;
import filemanagement.permission.service.classification.ClassifyByCustomCategory;
import filemanagement.permission.service.classification.ClassifyByName;
import filemanagement.permission.service.classification.ClassifyBySize;
import filemanagement.permission.service.classification.ClassifyByType;
import filemanagement.permission.service.filerepository.DeleteFile;
import filemanagement.permission.service.filerepository.ExportFile;
import filemanagement.permission.service.filerepository.ImportFileNewVersion;
import filemanagement.permission.service.filerepository.ImportFileOverwrite;
import filemanagement.permission.service.versioncontrol.RollBack;

import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;

public class CachePermission {
    public static final ReadFile readFile = ReadFile.getInstance();
    public static final ImportFileNewVersion importFileNewVersion;

    static {
        importFileNewVersion = ImportFileNewVersion.getInstance();
    }

    public static final ImportFileOverwrite importFileOverwrite = ImportFileOverwrite.getInstance();
    public static final ExportFile exportFile = ExportFile.getInstance();
    public static final DeleteFile deleteFile = DeleteFile.getInstance();
    public static final RollBack rollBack = RollBack.getInstance();
     public static final ClassifyByType classifyByType = new ClassifyByType();
    public static final ClassifyBySize classifyBySize = new ClassifyBySize();
     public static final ClassifyByCustomCategory classifyByCustomCategory = new ClassifyByCustomCategory();
     public static final ClassifyByName classifyByName = new ClassifyByName();

    public static Map<String, Object> permissionMap = new HashMap<>();

    static {
        permissionMap.put("ReadFilePermission", readFile);
        permissionMap.put("RollBackPermission", rollBack);
        permissionMap.put("DeleteFilePermission", deleteFile);
        permissionMap.put("ClassifyByTypePermission", classifyByType);
        permissionMap.put("ClassifyBySizePermission", classifyBySize);
        permissionMap.put("ClassifyByNamePermission", classifyByName);
        permissionMap.put("ClassifyByCustomCategoryPermission", classifyByCustomCategory);
        permissionMap.put("ExportFilePermission", exportFile);
        permissionMap.put("ImportFileOverwritePermission", importFileOverwrite);
        permissionMap.put("ImportNewVersionPermission", importFileNewVersion);
    }









}