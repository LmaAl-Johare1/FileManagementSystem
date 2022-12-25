package filemanagement.usertype.users;

import filemanagement.exception.NameNotFoundException;
import filemanagement.exception.NoDataInFileJsonException;
import filemanagement.permission.CachePermission;
import filemanagement.permission.service.classification.ClassifyByCustomCategory;
import filemanagement.permission.service.classification.ClassifyBySize;
import filemanagement.permission.service.classification.ClassifyByType;
import filemanagement.permission.service.filerepository.ExportFile;
import filemanagement.permission.service.ReadFile;

import filemanagement.log.Loggers;
import filemanagement.permission.service.filerepository.ImportFileNewVersion;
import filemanagement.permission.service.versioncontrol.RollBack;

import java.io.IOException;
import java.util.Scanner;

public class Stuff extends User {
    private static Stuff instance;

    private Stuff() {}
    public static synchronized Stuff getInstance() {
        if (instance == null) {
            instance = new Stuff();
        }
        return instance;
    }

    @Override
    public void displayMenu() throws IOException, NameNotFoundException, NoDataInFileJsonException {
        Scanner scanner = new Scanner(System.in);
        boolean isExit = false;
        while (!isExit) {
            System.out.println("=============Stuff Menu============");
            System.out.println(" * Read files : 1");
            System.out.println(" * Import files with the new version : 2");
            System.out.println(" * Export files : 3");
            System.out.println(" * Classify files by :");
            System.out.println("    > Type : 4 ");
            System.out.println("    > Size : 5 ");
            System.out.println("    > Custom category by name : 6 ");
            System.out.println(" * Rollback feature : 7");
            System.out.println(" * Exit : 8");
            System.out.println("Enter number to operation : ");
            int option = Integer.parseInt(scanner.nextLine());
            if (option == 8) {
                isExit = true;
            } else {
                selectOption(option);
            }
        }
    }
    @Override
    public void selectOption(int option) throws IOException, NameNotFoundException, NoDataInFileJsonException {
        switch (option) {
            case 1 -> {
                ReadFile readFile = (ReadFile) CachePermission.permissionMap.get("ReadFilePermission");
                readFile.permission();
            }
            case 2 -> {
                ImportFileNewVersion importFileNewVersion = (ImportFileNewVersion) CachePermission.permissionMap.get("ImportNewVersionPermission");
                importFileNewVersion.permission();
            }
            case 3 -> {
                ExportFile exportFile = (ExportFile) CachePermission.permissionMap.get("ExportFilePermission");
                exportFile.permission();
            }
            case 4 -> {
                ClassifyByType classifyByType = (ClassifyByType) CachePermission.permissionMap.get("ClassifyByTypePermission");
                classifyByType.permission();
            }
            case 5 -> {
                ClassifyBySize classifyBySize = (ClassifyBySize) CachePermission.permissionMap.get("ClassifyBySizePermission");
                classifyBySize.permission();
            }
            case 6 -> {
                ClassifyByCustomCategory classifyByCustomCategory = (ClassifyByCustomCategory) CachePermission.permissionMap.get("ClassifyByCustomCategoryPermission");
                classifyByCustomCategory.permission();
            }
            case 7 -> {
                RollBack rollBack = (RollBack) CachePermission.permissionMap.get("RollBackPermission");
                rollBack.permission();
            }
            default -> Loggers.logError("Invalid option selected.");
        }
    }
}
