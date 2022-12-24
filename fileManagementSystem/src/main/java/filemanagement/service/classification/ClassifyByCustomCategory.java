package filemanagement.service.classification;
import filemanagement.model.FileModel;
import filemanagement.service.exception.JsonReadingException;
import filemanagement.service.exception.NameNotFoundException;
import filemanagement.service.exception.NoFileException;
import filemanagement.service.log.Loggers;

import java.util.*;

public class ClassifyByCustomCategory implements IClassify{
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
    public List<FileModel> classify() throws JsonReadingException, NoFileException,NameNotFoundException {
        ReadFileInfoFromJson read = new ReadFileInfoFromJson();
        List<FileModel> FileModels = read.getFileInfoFromJson();
        List<FileModel> openFolderFileModels = new ArrayList<>();
        List<FileModel> defaultFolderFileModels = new ArrayList<>();

        // list of open folder names
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
}