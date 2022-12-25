package filemanagement.permission.service.classification;
import filemanagement.exception.NoDataInFileJsonException;
import filemanagement.permission.IPermission;
import filemanagement.permission.model.FileModel;
import filemanagement.exception.JsonReadingException;
import filemanagement.exception.NameNotFoundException;
import filemanagement.log.Loggers;
import filemanagement.exception.NoFileException;

import java.util.*;

public class ClassifyByCustomCategory implements IClassify, IPermission {
    private static ClassifyByCustomCategory instance;

    private ClassifyByCustomCategory() {}
    public static synchronized ClassifyByCustomCategory getInstance() {
        if (instance == null) {
            instance = new ClassifyByCustomCategory();
        }
        return instance;
    }
    public List<String> getOpenFolders(String name) {
        List<String> openFolders = new ArrayList<>();
        openFolders.add(name);
        return openFolders;
    }
    public String getFolderName(){
        String name;
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter Folder name : ");
        name = scanner.nextLine();
        return name;
    }
    @Override
    public List<FileModel> classify() throws JsonReadingException, NoFileException, NameNotFoundException, NoDataInFileJsonException {
        ReadFileInfoFromJson read = new ReadFileInfoFromJson();
        List<FileModel> FileModels = read.getFileInfoFromJson();
        if (FileModels.isEmpty()) {
            throw new NoDataInFileJsonException("No data in file");
        }
        List<FileModel> openFolderFileModels = new ArrayList<>();
        List<FileModel> defaultFolderFileModels = new ArrayList<>();


        List<String> openFolders = getOpenFolders(getFolderName());

        for (FileModel fileModel : FileModels) {
            String fileName = fileModel.getFileName();
            if (openFolders.contains(fileName)) {
                openFolderFileModels.add(fileModel);
            } else {
                defaultFolderFileModels.add(fileModel);
            }
        }
        if(openFolderFileModels.isEmpty())
        {
            Loggers.logError("No files found with name: {}");
            throw new NameNotFoundException("No files found with name  " );
        }

        return openFolderFileModels;
    }

    @Override
    public void permission() throws NoFileException, NameNotFoundException, JsonReadingException, NoDataInFileJsonException {
        System.out.println("Sort by Custom Category :" + classify());
    }
}