package filemanagement.usertype.users;

import filemanagement.exception.JsonReadingException;
import filemanagement.exception.NameNotFoundException;
import filemanagement.exception.NoDataInFileJsonException;
import filemanagement.permission.CachePermission;
import filemanagement.permission.service.ReadFile;
import filemanagement.exception.NoFileException;
import filemanagement.log.Loggers;
import filemanagement.permission.service.classification.ClassifyByCustomCategory;
import filemanagement.permission.service.classification.ClassifyBySize;
import filemanagement.permission.service.classification.ClassifyByType;

import java.util.Scanner;

public class Reader extends User {

    private static Reader instance;

    private Reader() {}
    public static synchronized Reader getInstance() {
        if (instance == null) {
            instance = new Reader();
        }
        return instance;
    }
    @Override
    public void displayMenu() throws NoFileException, NameNotFoundException, JsonReadingException, NoDataInFileJsonException {
        Scanner scanner = new Scanner(System.in);
        boolean isExit = false;
        while (!isExit) {
            System.out.println("=============Reader Menu============");
            System.out.println(" * Read files : 1");
            System.out.println(" * Classify files by :");
            System.out.println("    > Type : 2 ");
            System.out.println("    > Size : 3 ");
            System.out.println("    > Custom category by name : 4 ");
            System.out.println(" * Exit : 5");
            System.out.println("Enter number to operation : ");
            int option = Integer.parseInt(scanner.nextLine());
            if (option == 5) {
                isExit = true;
            } else {
                selectOption(option);
            }
        }
    }
    @Override
    public void selectOption(int option) throws NoFileException, NameNotFoundException, JsonReadingException, NoDataInFileJsonException {
        switch (option) {
            case 1 -> {
                ReadFile readFile = (ReadFile) CachePermission.permissionMap.get("ReadFilePermission");
                readFile.permission();
            }
            case 2 -> {
                ClassifyByType classifyByType = (ClassifyByType) CachePermission.permissionMap.get("ClassifyByTypePermission");
                classifyByType.permission();
            }
            case 3 -> {
                ClassifyBySize classifyBySize = (ClassifyBySize) CachePermission.permissionMap.get("ClassifyBySizePermission");
                classifyBySize.permission();
            }
            case 4 -> {
                ClassifyByCustomCategory classifyByCustomCategory = (ClassifyByCustomCategory) CachePermission.permissionMap.get("ClassifyByCustomCategoryPermission");
                classifyByCustomCategory.permission();
            }
            default -> Loggers.logError("Invalid option selected.");
        }
    }
}
