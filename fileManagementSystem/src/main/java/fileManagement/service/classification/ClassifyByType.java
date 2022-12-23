package filemanagement.service.classification;
import filemanagement.model.FileModel;
import filemanagement.service.exception.JsonReadingException;
import filemanagement.service.exception.NoFileException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class ClassifyByType implements IClassify {
    @Override
    public List<FileModel> classify() throws JsonReadingException, NoFileException {
        ReadFileInfoFromJson read = new ReadFileInfoFromJson();
        List<FileModel> FileModels = read.getFileInfoFromJson();
        List<FileModel> sortedFileModels = new ArrayList<>(FileModels);

        Collections.sort(sortedFileModels, new Comparator<FileModel>() {
            @Override
            public int compare(FileModel o1, FileModel o2) {
                return o1.getFileType().compareTo(o2.getFileType());
            }
        });

        return sortedFileModels;
    }
}