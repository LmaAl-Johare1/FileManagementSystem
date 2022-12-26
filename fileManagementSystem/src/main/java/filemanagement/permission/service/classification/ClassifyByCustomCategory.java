package filemanagement.permission.service.classification;
import filemanagement.exception.NoDataInFileJsonException;
import filemanagement.permission.IPermission;
import filemanagement.permission.model.FileModel;
import filemanagement.exception.JsonReadingException;
import filemanagement.exception.NameNotFoundException;
import filemanagement.log.Loggers;
import filemanagement.exception.NoFileException;

import java.util.*;

import static filemanagement.permission.service.versioncontrol.Property.nameWithType;

public class ClassifyByCustomCategory implements IClassify, IPermission {
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
    public List<FileModel> classify() throws JsonReadingException, NameNotFoundException, NoDataInFileJsonException {
        ReadFileInfoFromJson read = new ReadFileInfoFromJson();
        List<FileModel> fileModels = read.getFileInfoFromJson();
        if (fileModels.isEmpty()) {
            throw new NoDataInFileJsonException("No data in file");
        }
        List<FileModel> openFolderFileModels = new ArrayList<>();
        List<FileModel> defaultFolderFileModels = new ArrayList<>();


        List<String> openFolders = getOpenFolders(getFolderName());

        for (FileModel fileModel : fileModels) {
            String fileName = fileModel.getFileName();
            String filename = fileName.substring(0, fileName.lastIndexOf('('));
            if (openFolders.contains(filename)) {
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