package filemanagement.permission.service.classification;
import filemanagement.exception.NoDataInFileJsonException;
import filemanagement.permission.model.FileModel;
import filemanagement.exception.JsonReadingException;
import filemanagement.exception.NoFileException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class ClassifyByType implements IClassify {
    @Override
    public List<FileModel> classify() throws JsonReadingException, NoFileException, NoDataInFileJsonException {
        ReadFileInfoFromJson read = new ReadFileInfoFromJson();
        List<FileModel> FileModels = read.getFileInfoFromJson();
        if (FileModels.isEmpty()) {
            throw new NoDataInFileJsonException("No data in file");
        }
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