package filemanagement.permission.service.classification;
import filemanagement.exception.NameNotFoundException;
import filemanagement.exception.NoDataInFileJsonException;
import filemanagement.permission.IPermission;
import filemanagement.permission.model.FileModel;
import filemanagement.exception.JsonReadingException;
import filemanagement.exception.NoFileException;

import java.util.*;

public class ClassifyByName implements  IClassify, IPermission {
    @Override
    public List<FileModel> classify() throws JsonReadingException, NoFileException, NoDataInFileJsonException {
        ReadFileInfoFromJson read = new ReadFileInfoFromJson();
        List<FileModel> fileModels = read.getFileInfoFromJson();
        if (fileModels.isEmpty()) {
            throw new NoDataInFileJsonException("No data in file");
        }
        List<FileModel> sortedFileModels = new ArrayList<>(fileModels);

        Collections.sort(sortedFileModels, new Comparator<FileModel>() {
            @Override
            public int compare(FileModel o1, FileModel o2) {
                return o1.getFileName().compareTo(o2.getFileName());
            }
        });
        return sortedFileModels;
    }

    @Override
    public void permission() throws NoFileException, NameNotFoundException, JsonReadingException, NoDataInFileJsonException {
        System.out.println("Sort by Name :" + classify());
    }
}