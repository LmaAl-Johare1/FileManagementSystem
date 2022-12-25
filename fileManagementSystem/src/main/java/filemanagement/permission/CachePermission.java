package filemanagement.permission;

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
import filemanagement.permission.service.versioncontrol.VersionControl;

import java.util.HashMap;
import java.util.Map;

public class CachePermission {
    public static final ReadFile readFile = ReadFile.getInstance();
    public static final ImportFileNewVersion importFileNewVersion = ImportFileNewVersion.getInstance();
    public static final ImportFileOverwrite importFileOverwrite = ImportFileOverwrite.getInstance();
    public static final ExportFile exportFile = ExportFile.getInstance();
    public static final DeleteFile deleteFile = DeleteFile.getInstance();
    public static final RollBack rollBack = RollBack.getInstance();
     public static final ClassifyByType classifyByType = ClassifyByType.getInstance();
    public static final ClassifyBySize classifyBySize = ClassifyBySize.getInstance();
     public static final ClassifyByCustomCategory classifyByCustomCategory = ClassifyByCustomCategory.getInstance();

     public static final ClassifyByName classifyByName = ClassifyByName.getInstance();



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