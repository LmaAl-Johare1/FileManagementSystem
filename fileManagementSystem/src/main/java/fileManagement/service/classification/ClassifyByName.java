package filemanagement.service.classification;
import filemanagement.model.FileModel;
import filemanagement.service.exception.JsonReadingException;
import filemanagement.service.exception.NoFileException;

import java.util.*;

public class ClassifyByName implements  IClassify{
    @Override
    public List<FileModel> classify() throws JsonReadingException, NoFileException {
        ReadFileInfoFromJson read = new ReadFileInfoFromJson();
        List<FileModel> FileModels = read.getFileInfoFromJson();
        List<FileModel> sortedFileModels = new ArrayList<>(FileModels);

        Collections.sort(sortedFileModels, new Comparator<FileModel>() {
            @Override
            public int compare(FileModel o1, FileModel o2) {
                return o1.getFileName().compareTo(o2.getFileName());
            }
        });

        return sortedFileModels;
    }

}